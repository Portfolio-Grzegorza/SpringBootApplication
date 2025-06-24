package com.exerciseApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    private boolean completed;

    //wiele itemow listy nalezy do jednej listy
    @ManyToOne
    @JoinColumn(name = "exercise_list_id") //przechowuje klucz obcy do exercise list
    private ExerciseList exerciseList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public ExerciseList getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(ExerciseList exerciseList) {
        this.exerciseList = exerciseList;
    }
}
