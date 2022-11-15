package com.example.demo.service;
import com.example.demo.DTO.Backlog;
import com.example.demo.DTO.Project;
import com.example.demo.DTO.User;
import com.example.demo.Exceptions.ProjectIdException;
import com.example.demo.repository.BacklogRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private UserRepository userRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            if(project.getId()==null)
            {
                Backlog backlog=new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }
            if(project.getId()!=null)
            {
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));

            }
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("project id " + project.getProjectIdentifier().toUpperCase() + " already exists");
        }
    }
    public User createUser(User user)
    {
        return userRepository.save(user);
    }

//    public Project findProjectByIdentifier(String ProjectId)
//    {
//        System.out.println("enteted in findProjectByIdentifier:");
//        Project project=projectRepository.findByprojectIdentifier(ProjectId);
//        if(project==null)
//        {
//            System.out.println("project is null");
//            throw new ProjectIdException("project id "+ ProjectId + "does not exist");
//        }
//        return project;
//    }


    public Project findProjectByIdentifier(String projectIdentifier) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project == null || project.getProjectIdentifier() == null) {
            throw new ProjectIdException("The project with the id " + projectIdentifier + " doesnt exist..!");
        }
        return projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
    }


    public Iterable<Project> findAllProjects()
    {
        return projectRepository.findAll();
    }
    public void deleteByprojectIdentifier(String ProjectId)
    {
        Project project=projectRepository.findByProjectIdentifier(ProjectId);
        if(project==null)
        {
            throw new ProjectIdException("project with id did not exist");
        }
        projectRepository.delete(project);
    }
}
