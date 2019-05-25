package com.java.pgs.wypozyczalnia.repository;

import com.java.pgs.wypozyczalnia.domain.Item;
import com.java.pgs.wypozyczalnia.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    User findUserByEmail(String email);
    List<User> findByLastname(String lastname);
}
