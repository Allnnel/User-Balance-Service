package com.example.repository;

import com.example.model.Balance;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);

    void deleteById(long id);
    User save(User user);
}
