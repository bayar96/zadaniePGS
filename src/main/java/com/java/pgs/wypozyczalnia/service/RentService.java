package com.java.pgs.wypozyczalnia.service;

import com.java.pgs.wypozyczalnia.domain.Item;
import org.springframework.hateoas.Resource;

import java.net.URISyntaxException;

public interface RentService {

    Resource<Item> rentItemById(Long ItemId) throws URISyntaxException;


}
