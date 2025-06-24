package com.exerciseApp.service;

import com.exerciseApp.entity.ExerciseList;
import com.exerciseApp.entity.User;
import com.exerciseApp.repository.ExerciseListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExerciseListService {

    private final ExerciseListRepository exerciseListRepository;

    public ExerciseListService(ExerciseListRepository exerciseListRepository) {
        this.exerciseListRepository = exerciseListRepository;
    }

    public ExerciseList create(ExerciseList list) {
        return exerciseListRepository.save(list);
    }

    public List<ExerciseList> findByUser(User user) {
        return exerciseListRepository.findByUser(user);
    }
    public Optional<ExerciseList> findById(Long id) {
        return exerciseListRepository.findById(id);
    }
    public void delete(Long id) {
        exerciseListRepository.deleteById(id);
    }
    public ExerciseList save(ExerciseList list) {
        return exerciseListRepository.save(list);
    }


}
