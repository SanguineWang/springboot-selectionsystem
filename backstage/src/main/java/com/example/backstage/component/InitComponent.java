package com.example.backstage.component;

import com.example.backstage.entity.Teacher;
import com.example.backstage.entity.User;
import com.example.backstage.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InitComponent implements InitializingBean {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private TeacherService teacherService;

    @Override
    public void afterPropertiesSet() throws Exception {
        User user = new User();
        user.setName("SanguineWang");
        user.setNumber(2017000002);
        user.setPassword( encoder.encode("123456"));
        Teacher teacher = new Teacher();
        teacherService.addTeacher(teacher, user);
    }
}
