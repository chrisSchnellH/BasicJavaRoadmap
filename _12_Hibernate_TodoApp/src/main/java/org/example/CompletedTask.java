package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "completed_tasks")
public class CompletedTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "completed_date", nullable = false)
    private String completedDate;

    public CompletedTask() {}

    public CompletedTask(String description, String completedDate) {
        this.description = description;
        this.completedDate = completedDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    @Override
    public String toString() {
        return String.format("%d: %s (Completed on: %s)", id, description, completedDate);
    }
}

