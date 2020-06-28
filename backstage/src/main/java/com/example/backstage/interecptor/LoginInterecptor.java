package com.example.backstage.interecptor;

import com.example.backstage.component.EncryptComponent;
import com.example.backstage.component.Mytoken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
@Slf4j
public class LoginInterecptor implements HandlerInterceptor {
    @Autowired
    private EncryptComponent encryptComponent;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional.ofNullable(request.getHeader(Mytoken.AUTHORIZATION))
                .map(auth-> encryptComponent.decryptToken(auth))
                .ifPresentOrElse(token->{
                    request.setAttribute(Mytoken.UID,token.getUid());
                    request.setAttribute(Mytoken.ROLE,token.getRole());
                    log.debug(token.getRole().toString());
                },()->{
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"未登录");
                });
        return true;
    }
}
