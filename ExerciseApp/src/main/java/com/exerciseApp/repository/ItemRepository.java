package com.exerciseApp.repository;

import com.exerciseApp.entity.Item;
import com.exerciseApp.entity.ExerciseList;
import org.springframework.data.jpa.repository.JpaRepository;
//interfejs w spring data, ktory umozliwia operacje Krut na encjach
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByExerciseList(ExerciseList exerciseList);
}
