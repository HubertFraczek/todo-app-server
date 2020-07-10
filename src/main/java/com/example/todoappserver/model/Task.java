package com.example.todoappserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "todo_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "task")
    private String task;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "creation_date")
    private Date creationDate;

    public Task() {

    }

    public Task(int id, User user, String task, boolean isActive, Date creationDate) {
        this.id = id;
        this.user = user;
        this.task = task;
        this.isActive = isActive;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public String getCreationDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        formatter.format(this.creationDate);
        return formatter.format(this.creationDate);
    }

    public void setCreationDate(String date) throws ParseException {
        this.creationDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);

    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", user=" + user +
                ", task='" + task + '\'' +
                ", isActive=" + isActive +
                ", creationDate=" + creationDate +
                '}';
    }
}
