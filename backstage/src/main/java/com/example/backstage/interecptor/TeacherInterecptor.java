package com.example.backstage.interecptor;

import com.example.backstage.component.RequestComponent;
import com.example.backstage.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class TeacherInterecptor implements HandlerInterceptor {
    @Autowired
    private RequestComponent requestComponent;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       User.Role role=requestComponent.getRole();

        if (requestComponent.getRole()!= User.Role.TEACHER){
           throw new ResponseStatusException(HttpStatus.FORBIDDEN,"无权限");
       }
        log.debug(" TeacherInterecptor running");
        return true;
    }
}
