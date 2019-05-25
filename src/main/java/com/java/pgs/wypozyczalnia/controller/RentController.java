package com.java.pgs.wypozyczalnia.controller;

import com.java.pgs.wypozyczalnia.domain.Item;
import com.java.pgs.wypozyczalnia.service.RentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class RentController {

    @Autowired
    private RentServiceImpl rentService;

    @GetMapping(value = "/rent/{id}", produces = "application/json; charset=UTF-8")
    public Resource<Item> rentById(@PathVariable Long id) throws URISyntaxException { return rentService.rentItemById(id); }

}
