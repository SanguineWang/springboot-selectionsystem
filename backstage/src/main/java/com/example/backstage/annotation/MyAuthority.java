package com.example.backstage.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface MyAuthority {
    MyAuthorityType [] value() default MyAuthorityType.STUDENT;
    public enum MyAuthorityType{
        STUDENT,
        TEACHER,
        ADMIN
    }
}
