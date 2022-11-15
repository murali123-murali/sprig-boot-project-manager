package com.example.demo.repository;

import com.example.demo.DTO.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long> {

    List<ProjectTask> findByProjectIdentifier(String id);
    ProjectTask findByProjectSequence(String pt_id);
}
