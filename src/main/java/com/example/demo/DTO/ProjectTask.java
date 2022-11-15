package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class ProjectTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(updatable = false)
    private String projectSequence;
    @NotBlank(message = "please include project summary")
    private String summary;

    private String acceptanceCriteria;
    private String status;
    private Integer priority;
    private Integer due_Date;

    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name="backlog_id",updatable = false,nullable = false)
    @JsonIgnore
    private Backlog backlog;

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    @Column(updatable = false)
    private  String projectIdentifier;

    private Date created_at;
    private  Date updated_at;

    public ProjectTask()
    {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getProjectSequence() {
        return projectSequence;
    }

    public void setProjectSequence(String projectSequence) {
        this.projectSequence = projectSequence;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getDue_Date() {
        return due_Date;
    }

    public void setDue_Date(Integer due_Date) {
        this.due_Date = due_Date;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
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

    @PrePersist
    protected void onCreate()
    {
        this.created_at=new Date();
    }
    @PreUpdate
    protected  void onUpdate()
    {
        this.updated_at=new Date();
    }

    @Override
    public String toString() {
        return "ProjectTask{" +
                "id=" + Id +
                ", projectSequence='" + projectSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", dueDate=" + due_Date +
                ", backlog=" + backlog +
                ", projectIdentifier='" + projectIdentifier + '\'' +
                ", create_At=" + created_at +
                ", update_At=" + updated_at +
                '}';
    }


}
