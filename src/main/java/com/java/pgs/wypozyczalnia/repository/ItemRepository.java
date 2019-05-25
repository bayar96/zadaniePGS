package com.java.pgs.wypozyczalnia.repository;

import com.java.pgs.wypozyczalnia.domain.Item;
import com.java.pgs.wypozyczalnia.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);
}
