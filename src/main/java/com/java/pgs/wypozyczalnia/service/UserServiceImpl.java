package com.java.pgs.wypozyczalnia.service;

import com.java.pgs.wypozyczalnia.controller.UserController;
import com.java.pgs.wypozyczalnia.domain.Role;
import com.java.pgs.wypozyczalnia.domain.User;
import com.java.pgs.wypozyczalnia.error.UserNotFoundException;
import com.java.pgs.wypozyczalnia.repository.RoleRespository;
import com.java.pgs.wypozyczalnia.repository.UserRepository;
import com.java.pgs.wypozyczalnia.resourceAssembler.UserResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRespository roleRespository;
    @Autowired
    private UserResourceAssembler userResourceAssembler;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;




    public Resources<Resource<User>> fingAllUsers() {
        List<Resource<User>> users = userRepository.findAll().stream()
                .map(userResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(users,
                linkTo(methodOn(UserController.class).retrieveAllUsers()).withSelfRel());
    }
    public Resource<User> findUser(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = findUserByEmail(auth.getName());
        if(user.getId().equals(id)){
            user = userRepository.findById(id).
                    orElseThrow(() -> new UserNotFoundException(id));
        return userResourceAssembler.toResource(user);}
        else
            return null;
    }
    public Resource<User> findByEmail(String email) {
        User user = userRepository.findByEmail(email).
                orElseThrow(() -> new UserNotFoundException(email));
        return userResourceAssembler.toResource(user);
    }
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).
                orElseThrow(() -> new UserNotFoundException(email));
        return user;
    }
    public Resources<Resource<User>> findByLastName(String lastName) {
        List<Resource<User>> users = userRepository.findByLastname(lastName).stream()
                .map(userResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(users,
                linkTo(methodOn(UserController.class).retrieveAllUsers()).withSelfRel());
    }
    public ResponseEntity<?> createUser(User newuser) throws URISyntaxException {

        Resource<User> user = userResourceAssembler.toResource(userRepository.save(newuser));

        return ResponseEntity.created(new URI(user.getId().expand().getHref())).body(user);

    }
    public ResponseEntity<?> updateUser(User newuser, Long id) throws URISyntaxException {

        User updatedUser = userRepository.findById(id)
                .map(user -> {
                    user.setFirstname(newuser.getFirstname());
                    user.setLastname(newuser.getLastname());
                    user.setEmail(newuser.getEmail());
                    user.setPassword(newuser.getPassword());
                    user.setItems(newuser.getItems());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newuser.setId(id);
                    return userRepository.save(newuser);
                });

        Resource<User> resource = userResourceAssembler.toResource(updatedUser);
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }
    public ResponseEntity<?> deleteUser(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRespository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

}
