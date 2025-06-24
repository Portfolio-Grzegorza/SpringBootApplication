package com.exerciseApp.repository;

import com.exerciseApp.entity.ExerciseList;
import com.exerciseApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository; //interfejs w spring data, ktory umozliwia operacje Krut na encjach
import java.util.List;

public interface ExerciseListRepository extends JpaRepository<ExerciseList, Long> {
    List<ExerciseList> findByUser(User user);
}
