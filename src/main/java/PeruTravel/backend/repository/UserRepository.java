package PeruTravel.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import PeruTravel.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}