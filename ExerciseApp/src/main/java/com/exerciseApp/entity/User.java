package com.exerciseApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;


@Entity //encja jpa
@Table(name = "Exercise_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotEmpty
    @Column(unique = true) //wymuszamy unikalnosc adresow email w naszej bazie danych
    private String email;

    @NotEmpty
    private String password;

    //jeden uzytkownik moze miec wiele list
    //okreslanie relacji
    //cascade - gwarantuje ze przy usunieciu uzytkownika usuwa rowniez jego listy
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseList> exerciseLists;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public @NotEmpty String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    public List<ExerciseList> getExerciseLists() {
        return exerciseLists;
    }

    public void setExerciseLists(List<ExerciseList> exerciseLists) {
        this.exerciseLists = exerciseLists;
    }
}
