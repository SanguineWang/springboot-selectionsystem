package com.example.backstage.service;

import com.example.backstage.entity.*;
import com.example.backstage.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ChooseDirectionReporsitory chooseDirectionReporsitory;
    @Autowired
    private DirectionRepository directionRepository;


    /**
     * 获取个人信息
     */
    public Student getMyInfo(Integer sid) {
        return studentRepository.findById(sid).get();
    }

    /**
     * @return 获取教师集合
     */
    public List<Teacher> getTeachers(){
        return teacherRepository.findAll();
    }



    /**
     * 选择导师
     */
    public synchronized Student pick(Integer sid, Integer tid) throws HttpStatusCodeException {
        Student student = studentRepository.findById(sid).get();//登录之后非空
        Teacher teacher = teacherRepository.findById(tid).get();//点击之后跳转非空，但是可能被篡改地址栏
           if (teacher.getUpper_limit()>=teacher.getStudentList().size())
           {   if(teacher.getMark_limit()<=student.getGrade()){
               student.setTeacher(teacher);
               studentRepository.save(student);
           }else {
               throw new ResponseStatusException(HttpStatus.FORBIDDEN, "你成绩不够");
           }
           }
           else {
               throw new ResponseStatusException(HttpStatus.FORBIDDEN, "人数已满");
           }
        return student;
    }

    /**获取指定教师所教课程的成绩单
     * @param sid
     * @param tid
     * @return
     */
    public List<Elective> getScoreSheet(Integer sid,Integer tid){
       return studentRepository.findById(sid).get().getElectiveList()
               .stream().filter(elective -> elective.getCourse().getTeacher().getId().equals(tid))
               .collect(Collectors.toList());
    }


    /**
     * @return 获取指定教师的方向
     */
    public List<Direction> getTeachersDirections(Integer tid){
        return teacherRepository.findById(tid).get().getDirectionList();
    }
    /**
     * @param sid 学生选择方向 ,再次选择删除自己的方向
     * @param directions
     * @return
     */
    public  void ChooseDirections(Integer sid,List<Direction> directions){
       chooseDirectionReporsitory.removeBySid(sid);
       Student student= studentRepository.findById(sid).get();
       for (Direction d :directions){
         Direction direction= directionRepository.findById(d.getId()).get();
         ChooseDirection chooseDirection =new ChooseDirection();
         chooseDirection.setDirection(direction);
         chooseDirection.setStudent(student);
         chooseDirectionReporsitory.saveAndFlush(chooseDirection);
       }
    }

    /**获取我选择的方向
     * @param sid
     * @return
     */
    public List<Direction>  getMyDirection(Integer sid){
        Student student= studentRepository.findById(sid).get();
        return Optional.ofNullable(directionRepository.findDirectionsByStudentId(sid)).orElse(List.of()) ;
    }

    public int getTeacherSelected(Integer tid) {
        return teacherRepository.findById(tid).get().getStudentList().size();
    }
}
