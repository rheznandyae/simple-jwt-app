package test.simplejwtapp.javadev.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.simplejwtapp.javadev.user.core.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}