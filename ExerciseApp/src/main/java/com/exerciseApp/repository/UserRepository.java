package com.exerciseApp.repository;

import com.exerciseApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository; //interfejs w spring data, ktory umozliwia operacje Krut na encjach
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
