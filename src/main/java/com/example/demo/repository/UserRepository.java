package com.example.demo.repository;

import com.example.demo.DTO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
