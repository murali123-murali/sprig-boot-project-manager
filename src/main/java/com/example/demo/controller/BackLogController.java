package com.example.demo.controller;

import com.example.demo.DTO.ProjectTask;
import com.example.demo.service.MapValidationService;
import com.example.demo.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BackLogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MapValidationService mapValidationService;

    @PostMapping(value = "/{backlog_id}")
    public ResponseEntity<?> addToBacklog(@Valid @RequestBody ProjectTask projectTask,
                                          BindingResult result, @PathVariable String backlog_id) {
        ResponseEntity<?> errormap = mapValidationService.mapValidationService(result);
        if (errormap != null)
            return errormap;
        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);
        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id) {

        return projectTaskService.findBacklogById(backlog_id);

    }

    @GetMapping("/{backlog_id}/{pt_id}")
    public ProjectTask getProjectTask(@Valid @PathVariable String backlog_id, @PathVariable String pt_id) {
        ProjectTask projectTask = projectTaskService.getProjectTask(pt_id, backlog_id);
        return projectTask;
    }

    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectSequence(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
                                                   @PathVariable String backlog_id, @PathVariable String pt_id) {
        ResponseEntity<?> errormap = mapValidationService.mapValidationService(result);
        if (errormap != null)
            return errormap;
        ProjectTask updatedTask = projectTaskService.updateProjSeq(projectTask, pt_id, backlog_id);
        return new ResponseEntity<ProjectTask>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@Valid @PathVariable String backlog_id, @PathVariable String pt_id) {
        projectTaskService.deletePTask(backlog_id, pt_id);
        return new ResponseEntity<String>("project task with id " + pt_id + " deleted successfully", HttpStatus.OK);
    }


}
