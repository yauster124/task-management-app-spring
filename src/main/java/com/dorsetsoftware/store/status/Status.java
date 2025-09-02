package com.dorsetsoftware.store.status;

import jakarta.persistence.*;

import java.util.List;

import com.dorsetsoftware.store.task.Task;

@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @OneToMany(mappedBy = "status")
    private List<Task> tasks;

    public Status() {
    }

    public Status(String title) {
        this.title = title;
    }

    public Status(String title, List<Task> tasks) {
        this.title = title;
        this.tasks = tasks;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
