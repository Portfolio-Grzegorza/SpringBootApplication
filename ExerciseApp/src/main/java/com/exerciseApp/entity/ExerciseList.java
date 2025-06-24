package com.exerciseApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ExerciseList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

    //wiele list do jednego uzytkownika
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //jedna lista ma wiele itemow
    // fetch wszystko laduje sie razem z lista
    @OneToMany(mappedBy = "exerciseList", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();

    //dodawanie item do listy
    public void addItem(Item item) {
        if (items == null) {
            items = new ArrayList<>();
        }

        items.add(item);
        item.setExerciseList(this);
    }
    //usuwanie item
    public void removeItem(Item item) {
        items.remove(item);
        item.setExerciseList(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
