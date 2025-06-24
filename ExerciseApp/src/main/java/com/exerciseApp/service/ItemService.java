package com.exerciseApp.service;

import com.exerciseApp.entity.ExerciseList;
import com.exerciseApp.entity.Item;
import com.exerciseApp.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //dodaje item a jezeli obiekt nie ma id to traktuje go jako nowa encje i wykonuje insert
    public Item add(Item item) {
        return itemRepository.save(item);
    }


    public List<Item> findByExerciseListId(Long exerciseListId) {
        return itemRepository.findByExerciseList(new ExerciseList(){{
            setId(exerciseListId);
        }});
    }

    public Optional<Item> findById (Long id) {
        return itemRepository.findById(id);
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}
