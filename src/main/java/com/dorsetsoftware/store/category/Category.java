package com.dorsetsoftware.store.category;

import java.util.List;

import com.dorsetsoftware.store.task.Task;
import com.dorsetsoftware.store.user.User;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "categories")
    private List<Task> tasks;

    public Category() {
    }

    public Category(String title, User user) {
        this.title = title;
        this.user = user;
    }

    public Category(String title, List<Task> tasks, User user) {
        this.title = title;
        this.tasks = tasks;
        this.user = user;
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
