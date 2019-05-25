package com.java.pgs.wypozyczalnia.resourceAssembler;
import com.java.pgs.wypozyczalnia.controller.ItemController;
import com.java.pgs.wypozyczalnia.domain.Item;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ItemResourceAssembler implements ResourceAssembler<Item, Resource<Item>>{

    @Override
    public Resource<Item> toResource(Item item) {
        return new Resource<>(item,
                linkTo(methodOn(ItemController.class).retrieveItem(item.getId())).withSelfRel(),
                linkTo(methodOn(ItemController.class).retrieveAllItems()).withRel("items"));
    }
}
