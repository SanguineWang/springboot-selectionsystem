package com.example.backstage.service;

import com.example.backstage.entity.User;
import com.example.backstage.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(Integer number){
        return userRepository.findByNumber(number);
    }

}
