package com.exerciseApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exerciseApp.entity.ExerciseList;
import com.exerciseApp.entity.Item;
import com.exerciseApp.entity.User;
import com.exerciseApp.repository.ExerciseListRepository;
import com.exerciseApp.repository.ItemRepository;
import com.exerciseApp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

import java.util.List;


@SpringBootApplication
public class ExerciseAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExerciseAppApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(UserRepository userRepository,
							   ExerciseListRepository listRepository,
							   ItemRepository itemRepository,
							   PasswordEncoder passwordEncoder) {
		return args -> {
			String email = "demo567@example.com";
			if (userRepository.findByEmail(email).isEmpty()) {
				User user = new User();
				user.setEmail(email);
				user.setPassword(passwordEncoder.encode("password"));

				ExerciseList list = new ExerciseList();
				list.setTitle("Trening czwartek");
				list.setUser(user);

				Item item1 = new Item();
				item1.setName("Przysiady");
				item1.setCompleted(false);

				Item item2 = new Item();
				item2.setName("Wykroki");
				item2.setCompleted(false);

				list.addItem(item1);
				list.addItem(item2);

				user.setExerciseLists(Arrays.asList(list));
				userRepository.save(user);
			}

			System.out.println("=== Wszyscy użytkownicy ===");
			userRepository.findAll().forEach(u -> {
				System.out.println("Użytkownik: " + u.getEmail());
				if (u.getExerciseLists() != null) {
					u.getExerciseLists().forEach(list -> {
						System.out.println("  Lista: " + list.getTitle());
						if (list.getItems() != null) {
							list.getItems().forEach(item -> {
								System.out.println("    Ćwiczenie: " + item.getName());
							});
						}
					});
				}
			});
		};
	}



	// Dodawanie przykladowego uzytkownika
//	@Bean
//	CommandLineRunner initData(UserRepository userRepository,
//							   ExerciseListRepository listRepository,
//							   ItemRepository itemRepository,
//							   PasswordEncoder passwordEncoder) {
//		return args -> {
//			String email = "demo2@example.com";
//			if (userRepository.findByEmail(email).isEmpty()) {
//				User user = new User();
//				user.setEmail(email);
//				user.setPassword(passwordEncoder.encode("password"));
//
//				ExerciseList list = new ExerciseList();
//				list.setTitle("Trening wtorek");
//				list.setUser(user);
//
//				Item item1 = new Item();
//				item1.setName("Brzuszki");
//				item1.setCompleted(false);
//
//				Item item2 = new Item();
//				item2.setName("Pajacyki");
//				item2.setCompleted(false);
//
//				list.addItem(item1);
//				list.addItem(item2);
//
//				user.setExerciseLists(Arrays.asList(list));
//				userRepository.save(user);
//			}
//		};
//	}

//


}
