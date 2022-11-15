package com.example.demo.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "project name required")
    private String projectName;

    @NotBlank(message = "project identifier must not be blank")
    @Size(min = 4, max = 5, message = "The size of project identifier must be either 4 or 5")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "project")
    private Backlog backlog;

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @NotBlank(message = "project description must not be blank")
    private String description;

    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date start_date;

    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date end_date;

    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date created_at;

    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date updated_at;

    public Project() {

    }

    @PrePersist
    public void onCreate() {
        this.created_at = new Date();
    }

    @PostPersist
    protected void onUpdate() {
        this.updated_at = new Date();
    }
}
