package com.java.pgs.wypozyczalnia.resourceAssembler;

import com.java.pgs.wypozyczalnia.controller.UserController;
import com.java.pgs.wypozyczalnia.domain.User;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {
    @Override
    public Resource<User> toResource(User user) {
        return new Resource<>(user,
                linkTo(methodOn(UserController.class).retrieveUser(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).retrieveAllUsers()).withRel("users"));
    }
}
