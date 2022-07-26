package com.example.springproject.repository;

import com.example.springproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer>{

    UserEntity findByLogin(String login);
}
