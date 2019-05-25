package com.java.pgs.wypozyczalnia.service;

import com.java.pgs.wypozyczalnia.controller.ItemController;
import com.java.pgs.wypozyczalnia.domain.Item;
import com.java.pgs.wypozyczalnia.error.ItemNotFoundException;
import com.java.pgs.wypozyczalnia.repository.ItemRepository;
import com.java.pgs.wypozyczalnia.resourceAssembler.ItemResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service("itemService")
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemResourceAssembler itemResourceAssembler;
    @Autowired
    private UserServiceImpl userService;


    public Resources<Resource<Item>> retrieveAllItems() {
        List<Resource<Item>> items = itemRepository.findAll().stream()
                .map(itemResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(items,
                linkTo(methodOn(ItemController.class).retrieveAllItems()).withSelfRel());
    }
    @Override
    public Resource<Item> findById(Long id) {
        Item item = itemRepository.findById(id).
                orElseThrow(() -> new ItemNotFoundException(id));
        return itemResourceAssembler.toResource(item);
    }

    @Override
    public Resource<Item> findByName(String name) {
        Item item = itemRepository.findByName(name).
                orElseThrow(() -> new ItemNotFoundException(name));
        return itemResourceAssembler.toResource(item);
    }
    public ResponseEntity<?> createItem(Item newitem) throws URISyntaxException {
        Resource<Item> item = itemResourceAssembler.toResource(itemRepository.save(newitem));
        return ResponseEntity.created(new URI(item.getId().expand().getHref())).body(item);
    }
    public ResponseEntity<?> updateItem(Item newitem,Long id) throws URISyntaxException {
        Item updatedItem = itemRepository.findById(id)
                .map(item -> {
                    item.setName(newitem.getName());
                    item.setDescription(newitem.getDescription());
                    item.setQuantity(newitem.getQuantity());
                    item.setUsers(newitem.getUsers());
                    return itemRepository.save(item);
                })
                .orElseGet(() -> {
                    newitem.setId(id);
                    return itemRepository.save(newitem);
                });
        Resource<Item> resource = itemResourceAssembler.toResource(updatedItem);
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    public ResponseEntity<?> deleteItem(Long id) {
        itemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
