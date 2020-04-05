package com.example.backstage.component;

import com.example.backstage.entity.Teacher;
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

    }
}
