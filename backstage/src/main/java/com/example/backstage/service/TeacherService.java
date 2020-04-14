package com.example.backstage.service;

import com.example.backstage.entity.*;
import com.example.backstage.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class TeacherService {
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

    /**
     * 添加教师
     *
     * @param t teacher
     * @param u user
     */
    public void addTeacher(Teacher t, User u) {
        u.setRole(User.Role.TEACHER);
        t.setUser(u);
       Optional.ofNullable(userRepository.findByNumber(u.getNumber())).ifPresentOrElse(user -> {
            log.debug("\n用户- {} 已存在", user.getNumber());
        }, () -> {
            log.debug("\n添加教師用户-{}", u.getNumber());
            userRepository.save(u);
            teacherRepository.save(t);
        });
    }

    /**
     * 添加学生
     *
     * @param s Student
     * @param u User
     */
    public void addStudent(Student s, User u) {
        u.setRole(User.Role.STUDENT);
        s.setUser(u);
        Optional.ofNullable(userRepository.findByNumber(u.getNumber())).ifPresentOrElse(user -> {
            log.debug("\n用户- {} 已存在", user.getNumber());
        }, () -> {
            userRepository.save(u);
            studentRepository.save(s);
            log.debug("\n添加学生用户-{}", u.getNumber());
        });
    }

    /**
     * 教师添加课程
     *
     * @param c   course
     * @param tid teacher Id
     */
    public void addCourse(Course c, Integer tid) {
        teacherRepository.findById(tid).ifPresentOrElse(t -> {
            Optional.ofNullable(courseRepository.findByName(c.getName(), tid))
                    .ifPresentOrElse(course -> {
                        log.debug("\n该课程已存在");
                    }, () -> {
                        c.setTeacher(t);
                        courseRepository.save(c);
                        log.debug("\n添加课程成功-{} ", c.getName());
                    });
        }, () -> {
            log.debug("\n未找到该教师");
        });
    }

    /**
     * 教师添加方向
     *
     * @param d   direction
     * @param tid teacherId
     */
    public void addDirection(Direction d, Integer tid) {
        teacherRepository.findById(tid).ifPresentOrElse(t -> {
            Optional.ofNullable(directionRepository.findByName(d.getName(), tid))
                    .ifPresentOrElse(course -> {
                        log.debug("\n该方向已存在");
                    }, () -> {
                        d.setTeacher(t);
                        directionRepository.save(d);
                        log.debug("\n添加方向成功-{}", d.getName());
                    });
        }, () -> {
            log.debug("\n未找到该教师");
        });
    }

    /**
     * 为指定课程添加学生（默认其初始化了User属性）
     * 并录入成绩
     * 再次调用删除指定课程id的选课记录，相当于更新
     *
     * @param cid      courseId
     * @param students students
     */
    public void addStudentsToCourse(Integer cid, List<Student> students) {
        courseRepository.findById(cid).ifPresentOrElse(course -> {
            electiveReporsitory.remove(cid);
            students.forEach(s -> {
                log.debug("\n {}", s.getUser());
                Student student = Optional.ofNullable(studentRepository.findByNumber(s.getUser().getNumber()))
                        .orElseGet(() -> {
                            log.debug("\n {}", s.getUser());
                            userRepository.save(s.getUser());
                            log.debug("\n {}", s.getUser());
                            studentRepository.save(s);
                            log.debug("\n学生不存在,创建用户 number:{}", s.getUser().getNumber());
                            return studentRepository.refresh(s);
                        });
                Elective elective = new Elective();
                elective.setGrade(s.getGrade());
                elective.setCourse(course);
                elective.setStudent(student);
                electiveReporsitory.saveAndFlush(elective);
                electiveReporsitory.refresh(elective);
                log.debug("\n {} - 选课成功 - {} ：grade{}",
                        elective.getStudent().getUser().getNumber(),
                        elective.getCourse().getName(),
                        elective.getGrade());
            });
        }, () -> {
            log.debug("\n未找到该课程");
        });
    }

    /**
     * 老师提前添加敲定的学生 ，控制层配合addStudent使用，先创建user和student实体
     *
     * @param tid    teacherId
     * @param number studentId
     */
    public void addStudentForTeacher(Integer tid, Integer number) {
        teacherRepository.findById(tid).ifPresentOrElse(t -> {
            Student student = studentRepository.findByNumber(number);
            if (student.getTeacher() == null) {
                student.setTeacher(t);
            } else {
                log.debug("{} 已存在teacher ", student.getUser().getNumber());
            }
            studentRepository.saveAndFlush(student);
            log.debug("\n 教师{} 已 pick student：{}",
                    studentRepository.refresh(student).getTeacher().getUser().getNumber(),
                    number);
        }, () -> {
            log.debug("\n未找到该教师");
        });
    }

    /**
     * 移除指导的学生
     *
     * @param tid    tid
     * @param number number
     */
    public void removeStudent(Integer tid, Integer number) {
        Optional.ofNullable(studentRepository.findByNumberAndTid(number, tid))
                .ifPresentOrElse(s -> {
                    s.setTeacher(null);
                    studentRepository.saveAndFlush(s);
                    log.debug("\n student' teacher :{}", studentRepository.refresh(s).getTeacher());
                }, () -> {
                    log.debug("\n Student:{} 不存在或者没选 tid:{}的老师", number, tid);
                });
    }

    /**
     * 移除方向
     * @param did directionId
     */
    public void removeDirection( Integer did) {
        directionRepository.findById(did)
                .ifPresentOrElse(d -> {
                    directionRepository.deleteById(d.getId());
                    log.debug("删除成功");
                }, () -> {
                    log.debug("\n direction:{} 不存在", did);
                });
    }

    /**
     * 移除课程
     * @param cid courseId
     */
    public void removeCourse(Integer cid) {
        courseRepository.findById(cid)
                .ifPresentOrElse(c -> {
                    courseRepository.deleteById(c.getId());
                    log.debug("删除成功");
                }, () -> {
                    log.debug("\n course:{} 不存在", cid);
                });

    }


    /**
     * 教师的所有课程
     *
     * @param tid teacherId
     * @return courses
     */
    public List<Course> getCourses(Integer tid) {
        return Optional.ofNullable(courseRepository.findByTeacherId(tid))
                .orElse(List.of());
    }
    /**
     * 获取指定课程
     * @return courses
     */
    public Course getCourse(Integer cid) {
        return courseRepository.findById(cid)
                .orElse(new Course());
    }

    /**
     * 获取指定课程所有学生
     * @return courses
     */
    public List<Student> getStudentsFromCourse(Integer cid) {
        return  Optional.ofNullable(studentRepository.FromCourseGetStudent(cid))
                .orElse(List.of());
    }

    /**
     * 获取教师毕设方向
     *
     * @param tid teacherId
     * @return directions
     */
    public List<Direction> getDirections(Integer tid) {
        return Optional.ofNullable(directionRepository.findByTeacherId(tid))
                .orElse(List.of());
    }
    /**
     * 获取指定毕设方向
     *
     * @return directions
     */
    public Direction getDirection(Integer did) {
        return directionRepository.findById(did)
                .orElse(new Direction());
    }
    /**
     * 获取指定毕设方向所有学生
     * @return directions
     */
    public List<Student> getStudentsFromDirection(Integer did) {
        return Optional.ofNullable(studentRepository.FromDirectionGetStudent(did))
                .orElse(List.of());
    }


    /**
     * 获取教师指导学生集合
     *
     * @return teacher's students
     */
    public List<Student> getAllStudents() {
        return Optional.ofNullable(studentRepository.findAll())
                .orElse(List.of());
    }

    /**
     * 获取教师指导学生集合
     *
     * @param tid teacherId
     * @return teacher's students
     */
    public List<Student> getStudentList(Integer tid) {
        return Optional.ofNullable(studentRepository.findByTeacherId(tid))
                .orElse(List.of());
    }

    /**
     * 获取教师已指导学生数和指导限制人数
     *
     * @param tid teacherId
     * @return pick: ,limit:
     */
    public Map<String, Integer> getPickAndLimit(Integer tid) {
        Optional<Teacher> teacher = teacherRepository.findById(tid);
        if (teacher.isEmpty()) {
            log.debug("\n 未找到该教师 {}", tid);
            return null;
        } else {
            Integer pick = Optional.ofNullable(studentRepository.findByTeacherId(tid))
                    .orElse(List.of()).size();
            Integer limit = Optional.ofNullable(teacher.get().getUpper_limit())
                    .orElseGet(() -> Integer.valueOf("0"));
            log.debug("\n 已选：{} 名额：{}", pick, limit);
            return Map.of("pick", pick, "limit", limit);
        }
    }

    /**
     * 更新密码
     *
     * @param tid      teacherId
     * @param password password
     */
    public void updatePassword(Integer tid, String password) {
        teacherRepository.findById(tid).ifPresentOrElse(t -> {
            User user = t.getUser();
            user.setPassword(password);
            userRepository.saveAndFlush(user);
            log.debug("\n 新密码：{}", userRepository.refresh(user).getPassword());
        }, () -> {
            log.debug("\n 没有该教师{}", tid);
        });
    }

    /**
     * 更新教师其他信息，范围，指导学生数,
     *
     * @param tid         teacherId
     * @param upper_limit limit
     * @param mark_limit  mark
     */
    public void update(Integer tid, Integer upper_limit, Float mark_limit) {
        teacherRepository.findById(tid).ifPresentOrElse(t -> {
            t.setUpper_limit(upper_limit);
            t.setMark_limit(mark_limit);
            teacherRepository.saveAndFlush(t);
            log.debug("\n new：{}", teacherRepository.refresh(t));
        }, () -> {
            log.debug("\n 没有该教师{}", tid);
        });
    }

    /**
     * 修改课程信息
     *
     * @param cid        course id
     * @param courseName course name
     * @param weight     weight
     */
    public void updateCourse(Integer cid, String courseName, Float weight) {
        courseRepository.findById(cid).ifPresentOrElse(course -> {
            course.setName(courseName);
            course.setWeight(weight);
            courseRepository.saveAndFlush(course);
            log.debug("\n new :{}", courseRepository.refresh(course));
        }, () -> {
            log.debug("\n 未找到该课程：{}", cid);
        });
    }

    /**
     * 修改方向
     *
     * @param did  directionId
     * @param name direction name
     */
    public void updateDirection(Integer did, String name) {

        directionRepository.findById(did).ifPresentOrElse(direction -> {
            direction.setName(name);
            directionRepository.saveAndFlush(direction);
            log.debug("\n new :{}", directionRepository.refresh(direction));
        }, () -> {
            log.debug("\n 未找到该方向：{}", did);
        });

    }

    /**
     * 启动双选
     * 计算学生加权成绩
     */
    public void startAndCalculate(Integer tid) {
        teacherRepository.findById(tid).ifPresentOrElse(t -> {
            List<Student> students = studentRepository.findAll();
            students.forEach(s -> {
                        s.setGrade(Float.valueOf("0"));//初始化防止多次计算累计成绩
                        if (s.getElectiveList().size() != 0) {
//                            Float denominator = Float.valueOf("0");//加权分母
//                            denominator=denominator+ e.getCourse().getWeight();
                            //计算加权分子
                            Float denominator = ((Double) s.getElectiveList()
                                    .stream()
                                    .mapToDouble(e -> e.getCourse().getWeight()).sum())
                                    .floatValue();
                            log.debug("\n student:{} 累计学分比重 denominator:{}", s.getUser().getNumber(), denominator);
                            s.getElectiveList().forEach(e -> {
                                        e.getStudent().setGrade(e.getStudent().getGrade() + (e.getCourse().getWeight() * e.getGrade()));
                                        log.debug("\n student：{} | course：{} | grade {}", e.getStudent().getUser().getNumber(), e.getCourse().getName(), e.getGrade());
                                    }
                            );
                            s.setGrade(s.getGrade() / denominator);
                            log.debug("\n student:{} 选课数：{}", s.getUser().getNumber(), s.getElectiveList().size());
                        } else
                            log.debug("\n Student: {} 没有选课记录", s.getUser().getNumber());
                    }
            );
        }, () -> {
            log.debug("\n 没有该教师{}", tid);
        });
    }

    /**
     * 返回当前教师指定成绩范围的有资格的学生集合，按照加权成绩排序,(存入redis缓存)
     *
     * @param tid    teacherId
     * @param weight weight
     * @return Qualified students
     */
    public List<Student> getQualifiedstudents(Integer tid, Float weight) {
        return Optional.ofNullable(studentRepository.findByLimit(weight))
                .orElse(List.of());

    }

}
