package com.example.backstage.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootTest
public class PasswordEncoderTest {
    @Autowired
    private PasswordEncoder encoder;
    @Test
    public void test_Encoder(){
        String password="SanguineWang";
        String result=encoder.encode(password);

        log.debug(result);
        log.debug(encoder.encode(password));

        log.debug("{}",encoder.matches("SanguineWeng",result));
        log.debug("{}",encoder.matches("SanguineWang",result));

    }
}
