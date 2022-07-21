package com.example.springproject.repository;

import com.example.springproject.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, Integer> {

}
