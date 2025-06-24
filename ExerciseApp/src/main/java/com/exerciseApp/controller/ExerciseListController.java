package com.exerciseApp.controller;

import com.exerciseApp.entity.Item;
import com.exerciseApp.entity.ExerciseList;
import com.exerciseApp.entity.User;
import com.exerciseApp.service.ItemService;
import com.exerciseApp.service.ExerciseListService;
import com.exerciseApp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;


@Controller
@RequestMapping("/exerciselists")
public class ExerciseListController {

    private final ExerciseListService exerciseListService;

    private final ItemService itemService;

    private final UserService userService;

    public ExerciseListController(ExerciseListService exerciseListService,
                                  ItemService itemService,
                                  UserService userService) {
        this.exerciseListService = exerciseListService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping
    public String listExerciseLists(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email).orElse(null);
        model.addAttribute("lists", exerciseListService.findByUser(user));
        return "exerciselists";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("list", new ExerciseList());
        return "createlist";
    }

    @PostMapping("/create")
    public String createList(@ModelAttribute("list") ExerciseList list, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email).orElse(null);
        list.setUser(user);
        exerciseListService.create(list);
        return "redirect:/exerciselists";
    }

    @GetMapping("/view/{id}")
    public String viewList(@PathVariable Long id, Model model) {
        Optional<ExerciseList> optList = exerciseListService.findById(id);
        if (optList.isPresent()) {
            ExerciseList list = optList.get();
            model.addAttribute("list", list);
            model.addAttribute("newItem", new Item());
            return "viewList";
        }
        return "redirect:/exerciselists";
    }

    @PostMapping("/view/{id}/addItem")
    public String additem(@PathVariable Long id, @ModelAttribute("newItem") Item item) {
        item.setId(null);

        Optional<ExerciseList> optList = exerciseListService.findById(id);

        if (optList.isPresent()) {
            ExerciseList list = optList.get();
            item.setExerciseList(list);
            itemService.add(item);
        }
        return "redirect:/exerciselists/view" + id;
    }

    @GetMapping("/view/{listId}/editItem/{itemId}")
    public String editItemForm(@PathVariable long listId, @PathVariable Long itemId, Model model) {
        Optional<ExerciseList> optList = exerciseListService.findById(listId);
        if (optList.isPresent()) {
            ExerciseList list = optList.get();

            Optional<Item> optItem = itemService.findById(itemId);
            if (optItem.isPresent()) {
                model.addAttribute("list", list);
                model.addAttribute("item", optItem.get());
                return "editItem";
            }
        }

        return "redirect:/exerciselists/view" + listId;
    }

    @PostMapping("/view/{listId}/editItem/{itemId}")
    public String updateItem(@PathVariable Long listId, @PathVariable Long itemId,
                             @ModelAttribute("item") Item updatedItem) {
        Optional<Item> optItem = itemService.findById(itemId);
        if (optItem.isPresent()) {
            Item item = optItem.get();
            item.setName(updatedItem.getName());
            itemService.add(item);
        }
        return "redirect:/exerciselists/view" + listId;
    }

    @GetMapping("/edit/{id}")
    public String editListForm (@PathVariable Long id, Model model) {
        Optional<ExerciseList> optList = exerciseListService.findById(id);
        if (optList.isPresent()) {
            model.addAttribute("list", optList.get());
            return "editList";
        }
        return "redirect:/exerciselists";
    }

    @PostMapping("/edit/{id}")
    public String updateList(@PathVariable Long id, @ModelAttribute("list") ExerciseList updatedList) {
        Optional<ExerciseList> optList = exerciseListService.findById(id);
        if (optList.isPresent()) {
            ExerciseList existingList = optList.get();
            existingList.setTitle(updatedList.getTitle());
            exerciseListService.save(existingList);
        }
        return "redirect:/exerciselists";
    }

    @GetMapping("/view/{listId}/deleteItem/{itemId}")
    public String deleteItem(@PathVariable Long listId, @PathVariable Long itemId) {
        Optional<ExerciseList> optList = exerciseListService.findById(listId);
        if (optList.isPresent()) {
            ExerciseList list = optList.get();
            list.getItems().removeIf(item -> item.getId().equals(itemId));
            exerciseListService.save(list);
        }
        return "redirect:/exerciselists/view/" + listId;
    }

    @GetMapping("/delete/{id}")
    public String deleteList(@PathVariable Long id) {
        exerciseListService.delete(id);
        return "redirect:/exerciselists";
    }

}
