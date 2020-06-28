package com.example.backstage.service;

import com.example.backstage.entity.*;
import com.example.backstage.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.MethodWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class TeacherService {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private DirectionRepository directionRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ElectiveReporsitory electiveReporsitory;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChooseDirectionReporsitory chooseDirectionReporsitory;
    /**
     * 添加教师 同时持久化user
     *
     * @param t
     */
    public void addTeacher(Teacher t) {
        teacherRepository.save(t);
    }

    /**
     * 获取教师
     *
     * @param tid teacherId
     */
    public Teacher getTeacher(Integer tid) {
        return teacherRepository.findById(tid).orElse(new Teacher());
    }

    /**
     * 更新教师（除密码外）
     */
    public Teacher updateTeacher(Teacher t) {
        Teacher teacher = teacherRepository.findById(t.getId()).get();
        teacher.setMark_limit(t.getMark_limit());
        teacher.setUpper_limit(t.getUpper_limit());
        teacherRepository.saveAndFlush(teacher);
        return teacher;
    }

    /**
     * 获取教师指导学生集合
     *
     * @param tid teacherId
     * @return teacher's students
     */
    public List<Student> getMyStudents(Integer tid) {
        return studentRepository.findByTeacherId(tid);
    }

    /**
     * 添加老师提前敲定的学生 ，创建student对象并persist user
     */
    public Student addStudentForTeacher(Integer tid, User u) {
        Teacher teacher = teacherRepository.findById(tid).get();
        User user = userRepository.findByNumber(u.getNumber());
        if (user == null) {
            user = new User();
            user.setNumber(u.getNumber());
            user.setRole(User.Role.STUDENT);
            user.setName(u.getName());
            user.setPassword(encoder.encode("123456"));
            Student student = new Student();
            student.setTeacher(teacher);
            student.setUser(user);
            return studentRepository.saveAndFlush(student);
        } else {
            Student student = studentRepository.findById(user.getId()).get();
            student.setTeacher(teacher);
            return studentRepository.saveAndFlush(student);
        }
    }


    /**
     * 移除指导的学生
     *
     * @return teacher's students
     */
    public List<Student> removeMyStudents(List<Integer> studentIdList, Integer tid) {
        for (Integer id : studentIdList) {
            //因为是前端传值，所以存在
            Student student = studentRepository.findById(id).get();
            student.setTeacher(null);
            chooseDirectionReporsitory.removeBySid(student.getId());
            studentRepository.saveAndFlush(student);
        }
        return studentRepository.findByTeacherId(tid);
    }


    /**
     * 教师的所有课程
     */
    public List<Course> getCourses(Integer tid) {
        return teacherRepository.findById(tid).get().getCourseList();
    }

    /**
     * 获取指定课程
     *
     * @return courses
     */
    public Course getCourse(Integer cid) {
        return courseRepository.findById(cid).get();
    }

    /**
     * 移除课程
     *
     * @param cid courseId
     */
    public void removeCourse(Integer cid) {
        courseRepository.deleteById(cid);
    }

    /**
     * 修改课程信息
     */
    public Course updateCourse(Integer cid, Course c) {
        Course course = courseRepository.findById(cid).get();
        course.setName(c.getName());
        course.setWeight(c.getWeight());
        course.setExtra(c.getExtra());
        return courseRepository.saveAndFlush(course);
    }

    /**
     * 添加课程
     *
     * @param c   course
     * @param tid teacher Id
     */
    public void addCourse(Course c, Integer tid) {
        Teacher teacher = teacherRepository.findById(tid).get();
        Course course = new Course();
        course.setName(c.getName());
        course.setWeight(c.getWeight());
        course.setExtra(c.getExtra());
        course.setTeacher(teacher);
        courseRepository.saveAndFlush(course);
    }

    /**
     * 获取指定课程所有人的成绩
     *
     * @return courses
     */
    public List<Elective> getElectivesFromCourse(Integer cid) {
        return courseRepository.findById(cid).get().getElectiveList();
    }
//-----------------------------------待测试---测试成功---------------------------------------

    /**
     * 为指定课程添加学生,并录入成绩,再次调用删除指定课程id的选课记录，相当于更新
     *
     * @param cid      courseId
     * @param students students
     */
    public void addStudentsToCourse(Integer cid, List<Student> students) {
        Course course = courseRepository.findById(cid).get();
        electiveReporsitory.remove(cid);

        for (Student student: students)
        {
            Student  repStudent= addStudent(student);
            Elective elective = new Elective();
            elective.setGrade(student.getGrade());
            elective.setCourse(course);
            elective.setStudent(repStudent);
            electiveReporsitory.save(elective);
        }

    }

    /**
     * 数据库中没有该学生，添加学生 同时持久化user,如果存在，则返回
     */
    public Student addStudent(Student s) {
        Student student=studentRepository.findByNumber(s.getUser().getNumber());
        if(student==null){
            User user = new User();
            user.setNumber(s.getUser().getNumber());
            user.setName(s.getUser().getName());
            //固有信息
            user.setRole(User.Role.STUDENT);
            user.setPassword(encoder.encode("123456"));
            student = new Student();
            student.setUser(user);
            student=studentRepository.saveAndFlush(student);
//            studentRepository.refresh(student);
            log.debug(student.toString());
            return student;
        }else {
            return student;
        }
    }
//------------------------------------------------------------------

    /**
     * 添加方向
     *
     * @param d   direction
     * @param tid teacherId
     */
    public Direction addDirection(Direction d, Integer tid) {
        Teacher teacher = teacherRepository.findById(tid).get();
        Direction direction = new Direction();
        direction.setTeacher(teacher);
        direction.setName(d.getName());
        direction.setWeight(d.getWeight());
        return directionRepository.saveAndFlush(direction);
    }


    /**
     * 移除方向
     *
     * @param did directionId
     */
    public void removeDirection(Integer did) {
        directionRepository.findById(did)
                .ifPresentOrElse(d -> {
                    directionRepository.deleteById(d.getId());
                    log.debug("删除成功");
                }, () -> {
                    log.debug("\n direction:{} 不存在", did);
                });
    }

    /**
     * 修改方向
     */
    public Direction updateDirection(Integer did, Direction d) {

        Direction direction = directionRepository.findById(did).get();
        direction.setName(d.getName());
        return directionRepository.saveAndFlush(direction);
    }

    /**
     * 获取指定方向
     *
     * @return directions
     */
    public Direction getDirection(Integer did) {
        return directionRepository.findById(did)
                .orElse(new Direction());
    }

    /**
     * 获取教师方向集合
     *
     * @param tid teacherId
     * @return directions
     */
    public List<Direction> getDirections(Integer tid) {
        return Optional.ofNullable(directionRepository.findByTeacherId(tid))
                .orElse(List.of());
    }

    /**
     * 获取指定毕设方向所有学生
     *
     * @return directions
     */
    public List<Student> getStudentsFromDirection(Integer did) {
        return Optional.ofNullable(studentRepository.FromDirectionGetStudent(did))
                .orElse(List.of());
    }


    /**
     * 获取已选和限制人数
     *
     * @param tid teacherId
     */
    public Map getLimitedAndSelected(Integer tid) {
        Teacher teacher = teacherRepository.findById(tid).get();
        float limit =  Optional.ofNullable(teacher.getUpper_limit()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"未设置人数上限或分数上限"));

        return Map.of("limited", limit, "selected", teacher.getStudentList().size());

    }

//    ---------------------------------------------------------------

    /**
     * 启动双选,计算学生加权成绩,返回成绩满足的学生集合
     */
    public void startAndCalculate(Integer tid) {


        List<Student> students = studentRepository.findAll();
        students.forEach(s -> {
                    s.setGrade(null);//初始化防止多次计算累计成绩
                    List<Elective> electives = s.getElectiveList();
                    float denominator = 0;
                    float molecule = 0;
                    if (electives.size() != 0) {
                        for (Elective elective : electives) {
                            denominator += elective.getCourse().getWeight();
                            molecule += elective.getCourse().getWeight() * elective.getGrade();
                        }
                        s.setGrade(molecule / denominator);
                        studentRepository.saveAndFlush(s);
                    }
                }
        );
    }

    /**
     * 返回当前教师指定成绩范围的有资格的学生集合，按照加权成绩排序
     *
     * @param tid teacherId
     * @return Qualified students
     */
    public List<Student> getQualifiedstudents(Integer tid) {

        float markLimt =Optional.ofNullable(teacherRepository.findById(tid).get().getMark_limit()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"未设置分数上限或人数上限")) ;

        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getGrade() != null && student.getGrade() >= markLimt)
                .sorted(Comparator.comparing(Student::getGrade).reversed())
                .collect(Collectors.toList());
    }
    /**
     * 更新密码
     *
     * @param tid      teacherId

     */
    public void updatePassword(Integer tid, String oldPassword,String newPassword) {
        if (oldPassword.equals(newPassword)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"新密码不能与旧密码一致");
        }
        Teacher teacher = teacherRepository.findById(tid).get();
        if (encoder.matches(oldPassword,teacher.getUser().getPassword())){

            teacher.getUser().setPassword(encoder.encode(newPassword));
            teacherRepository.save(teacher);
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"密码错误");
        }
    }

}
