package com.example.demo.controller;

import com.example.demo.DTO.Project;
import com.example.demo.DTO.User;

import com.example.demo.service.MapValidationService;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/")

public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationService mapvalidationservice;



    public String HelloWorld()
    {
        return "hello murali";
    }

    @PostMapping
    public ResponseEntity<?> createNewProject(@Valid  @RequestBody Project project, BindingResult result)
    {
       ResponseEntity<?> errormap=mapvalidationservice.mapValidationService(result);
       if(errormap!=null)
           return errormap;
        Project project1=projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project1,HttpStatus.CREATED);
    }

    @PostMapping("add/user")
    public User createUser(@RequestBody User user){
        return projectService.createUser(user);
    }
    @GetMapping("/{ProjectId}")
    public ResponseEntity<?> getProjectByIdentifier(@PathVariable String ProjectId)
    {
        Project project=projectService.findProjectByIdentifier(ProjectId);
        return new ResponseEntity<Project>(project,HttpStatus.OK);
    }
    @GetMapping("/all")
    public Iterable<Project> getAllProjects()
    {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{ProjectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String ProjectId)
    {
        projectService.deleteByprojectIdentifier(ProjectId);
        return new ResponseEntity<String>("project with Id "+ProjectId+ "deleted successfully",HttpStatus.OK);
    }
}
