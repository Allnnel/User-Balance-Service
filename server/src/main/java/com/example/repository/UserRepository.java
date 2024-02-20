package com.example.repository;

import com.example.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findById(long id);

  Optional<User> findByLogin(String login);

  void deleteById(long id);

  void deleteByLogin(String login);
}
