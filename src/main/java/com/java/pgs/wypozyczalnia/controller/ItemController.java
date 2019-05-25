package com.java.pgs.wypozyczalnia.controller;
import com.java.pgs.wypozyczalnia.domain.Item;
import com.java.pgs.wypozyczalnia.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;


@RestController
public class ItemController {
    @Autowired
    private ItemServiceImpl itemService;

    @GetMapping(value = "/items", produces = "application/json; charset=UTF-8")
    public Resources<Resource<Item>> retrieveAllItems() {
        return itemService.retrieveAllItems();
    }
    @GetMapping(value = "/items/{id}", produces = "application/json; charset=UTF-8")
    public Resource<Item> retrieveItem(@PathVariable Long id) {
        return itemService.findById(id);
    }
    @GetMapping(value = "/items/search/byname/{name}", produces = "application/json; charset=UTF-8")
    public Resource<Item> findByName(@PathVariable String name) {
        return itemService.findByName(name);
    }
    @PostMapping(value = "/items", produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> createItem(@RequestBody Item newitem) throws URISyntaxException {
        return itemService.createItem(newitem);
    }
    @PutMapping(value = "/items/{id}", produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> updateItem(@RequestBody Item newitem, @PathVariable Long id) throws URISyntaxException {
        return itemService.updateItem(newitem,id);
    }
    @DeleteMapping(value = "/items/{id}", produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id);
    }

}
