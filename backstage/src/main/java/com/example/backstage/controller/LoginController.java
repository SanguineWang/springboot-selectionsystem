package com.example.backstage.controller;

import com.example.backstage.component.EncryptComponent;
import com.example.backstage.component.Mytoken;
import com.example.backstage.entity.User;
import com.example.backstage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/")
public class LoginController {
    @Value("teacher")
    private String teacherKey;
    @Value("student")
    private String studentKey;
    @Autowired
    private UserService userService;
    @Autowired
    private EncryptComponent encryptComponent;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("login")
    public Map login(@RequestBody User login, HttpServletResponse response) {
        User user = Optional.ofNullable(userService.getUser(login.getNumber()))
                .filter(user1 -> passwordEncoder.matches(login.getPassword(),user1.getPassword()))
                .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"账号或密码错误"));

        Mytoken mytoken=new Mytoken(user.getId(),user.getRole());
        String token = encryptComponent.encryptToken(mytoken);
        response.setHeader(Mytoken.AUTHORIZATION,token);
        String roleCode=  user.getRole()== User.Role.TEACHER ? teacherKey : studentKey;
        return Map.of("role", roleCode);
    }
}
