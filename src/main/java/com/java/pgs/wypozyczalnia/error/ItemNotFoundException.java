package com.java.pgs.wypozyczalnia.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends RuntimeException {

    private String name;
    private Long id;
    public ItemNotFoundException(Long id) {
        super(String.format(" not found item that with that id : '%s'",id));
        this.id=id;

    }
    public ItemNotFoundException(String name) {
        super(String.format(" not found with that name : '%s'",name));
        this.name=name;

    }
    public Long getId() {
        return this.id;
    }
}
