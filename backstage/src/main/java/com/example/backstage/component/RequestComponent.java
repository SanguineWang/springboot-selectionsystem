package com.example.backstage.component;

import com.example.backstage.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
@Slf4j
public class RequestComponent {
    public Integer getUid() {
        return (Integer) RequestContextHolder.currentRequestAttributes()
                .getAttribute(Mytoken.UID, RequestAttributes.SCOPE_REQUEST);
    }

    public User.Role getRole() {
        return (User.Role) RequestContextHolder.currentRequestAttributes()
                .getAttribute(Mytoken.ROLE, RequestAttributes.SCOPE_REQUEST);
    }
}
