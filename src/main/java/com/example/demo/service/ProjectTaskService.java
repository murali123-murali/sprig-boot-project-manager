package com.example.demo.service;

import com.example.demo.DTO.Backlog;
import com.example.demo.DTO.ProjectTask;
import com.example.demo.Exceptions.ProjectIdException;
import com.example.demo.repository.BacklogRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ProjectTaskService {
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {


        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        System.out.println(backlog);

        projectTask.setBacklog(backlog);


        Integer BacklogSequence = backlog.getPTSequence();
        BacklogSequence++;

        backlog.setPTSequence(BacklogSequence);

        projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + BacklogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        if (projectTask.getStatus() == "" || projectTask.getStatus() == null)
        {
            projectTask.setStatus("To_Do");
        }

        if ( projectTask.getPriority() == null) {
            projectTask.setPriority(3);
        }
        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask>  findBacklogById(String backlog_id)
    {
        return projectTaskRepository.findByProjectIdentifier(backlog_id);
    }

    public ProjectTask getProjectTask(String backlog_id,String pt_id) {
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if (backlog == null) {
            throw new ProjectIdException("backlog with id:" + backlog_id + "does not exist");
        }
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
        if (projectTask == null) {
            throw new ProjectIdException("project task with id " + pt_id + "does not exist");
        }
        if(!projectTask.getProjectIdentifier().equals(backlog_id))
        {
            throw new ProjectIdException("project task"+pt_id+"does not match");
        }
        return projectTask;
    }
    public  ProjectTask updateProjSeq(ProjectTask projectTask1,String pt_id,String backlog_id)
    {


        ProjectTask projectTask=getProjectTask(backlog_id,pt_id);
        projectTask=projectTask1;
        return projectTaskRepository.save(projectTask);
    }

    public void deletePTask(String backlog_id,String pt_id)
    {
        ProjectTask projectTask=getProjectTask(backlog_id,pt_id);
        projectTaskRepository.delete(projectTask);
    }
}
