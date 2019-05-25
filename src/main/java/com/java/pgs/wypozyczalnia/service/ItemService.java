package com.java.pgs.wypozyczalnia.service;

import com.java.pgs.wypozyczalnia.domain.Item;
import org.springframework.hateoas.Resource;

public interface ItemService {

    public Resource<Item> findById(Long id);
    public Resource<Item> findByName(String name);
}
