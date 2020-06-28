package com.example.backstage.component;

import com.example.backstage.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Mytoken{
    public static final String AUTHORIZATION ="authorization";
    public static final String UID="uid";
    public static final String ROLE="role";

    private Integer uid;
    private User.Role role;
}
