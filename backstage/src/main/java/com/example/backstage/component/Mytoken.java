package com.example.backstage.component;

import com.example.backstage.entity.User;
import lombok.Data;


@Data
public class Mytoken{
    public static final String AUTHORIZATION ="authorization";
    public static final String UID="uid";
    public static final String ROLE="role";

    private Integer uid;
    private User.Role role;
}
