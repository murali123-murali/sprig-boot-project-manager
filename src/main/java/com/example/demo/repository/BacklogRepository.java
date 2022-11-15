package com.example.demo.repository;

import com.example.demo.DTO.Backlog;
import com.example.demo.DTO.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog,Long>{
    Backlog findByProjectIdentifier(String Identifier);
}
