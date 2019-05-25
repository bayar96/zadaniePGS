package com.java.pgs.wypozyczalnia.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    private  String email;
    private Long id;
    public UserNotFoundException(Long id) {
        super(String.format(" not found user with that id: '%s'",id));
        this.id=id;

    }
    public UserNotFoundException(String email) {
        super(String.format(" not found with that email: '%s'",email));
        this.email=email;

    }
    public Long getId() {
        return this.id;
    }
}
