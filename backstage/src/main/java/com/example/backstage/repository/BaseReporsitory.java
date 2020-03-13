package com.example.backstage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseReporsitory<T, ID> extends JpaRepository<T, ID> {
    void refresh(T t);
}
