package com.example.demo.repository;
import com.example.demo.DTO.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long >
{


     @Override
     List<Project> findAll();

     Project findByProjectIdentifier(String toUpperCase);
}
