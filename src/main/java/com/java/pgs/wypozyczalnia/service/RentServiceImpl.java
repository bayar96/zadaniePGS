package com.java.pgs.wypozyczalnia.service;

import com.java.pgs.wypozyczalnia.domain.Item;
import com.java.pgs.wypozyczalnia.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.Collection;

@Service("rentService")
public class RentServiceImpl implements RentService {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ItemServiceImpl itemService;

    @Override
    public Resource<Item> rentItemById(Long itemId)throws URISyntaxException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Collection<Item> items = user.getItems();
        items.add(itemService.findById(itemId).getContent());
        user.setItems(items);
        userService.updateUser(user,user.getId());
        return itemService.findById(itemId);
    }



}
