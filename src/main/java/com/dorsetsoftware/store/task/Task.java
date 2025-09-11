package com.dorsetsoftware.store.task;

import java.time.LocalDate;
import java.util.Objects;
import java.util.List;

import jakarta.persistence.*;

import com.dorsetsoftware.store.category.Category;
import com.dorsetsoftware.store.comment.Comment;
import com.dorsetsoftware.store.status.Status;
import com.dorsetsoftware.store.user.User;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private LocalDate doBy;
    private Integer taskIndex;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToMany
    @JoinTable(name = "task_category", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Task() {
    }

    public Task(String title, String description, LocalDate doBy, Status status, List<Category> categories, List<Comment> comments, User user) {
        this.title = title;
        this.description = description;
        this.doBy = doBy;
        this.status = status;
        this.categories = categories;
        this.comments = comments;
        this.user = user;
    }

    public Task(String title, String description, LocalDate doBy, Integer taskIndex, Status status, List<Category> categories, List<Comment> comments, User user) {
        this.title = title;
        this.description = description;
        this.doBy = doBy;
        this.taskIndex = taskIndex;
        this.status = status;
        this.categories = categories;
        this.comments = comments;
        this.user = user;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDoBy() {
        return doBy;
    }

    public void setDoBy(LocalDate doBy) {
        this.doBy = doBy;
    }

    public Integer getTaskIndex() {
        return taskIndex;
    }

    public void setTaskIndex(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    // equals and hashCode based on id
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
