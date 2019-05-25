package com.java.pgs.wypozyczalnia.controller;
import com.java.pgs.wypozyczalnia.domain.User;
import com.java.pgs.wypozyczalnia.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URISyntaxException;


@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private MessageSource messages;


    @GetMapping(value = "/users", produces = "application/json; charset=UTF-8")
    public Resources<Resource<User>> retrieveAllUsers() {

        return userService.fingAllUsers();
    }
    @GetMapping(value = "/users/{id}", produces = "application/json; charset=UTF-8")
    public Resource<User> retrieveUser(@PathVariable long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        if(user.getId().equals(id))
            return userService.findUser(id);
        else
            return null;
    }
    @GetMapping(value = "/users/search/byemail/{email}", produces = "application/json; charset=UTF-8")
    public Resource<User> findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping(value = "/users/search/bylastname/{lastName}", produces = "application/json; charset=UTF-8")
    public Resources<Resource<User>> findByLastName(@PathVariable String lastName) { return userService.findByLastName(lastName); }
    @PostMapping(value = "/users", produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> createUser(@Valid @RequestBody User newuser) throws URISyntaxException { return userService.createUser(newuser); }
    @PutMapping(value = "/users/{id}", produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User newuser, @PathVariable long id) throws URISyntaxException { return userService.updateUser(newuser,id); }
    @DeleteMapping(value = "/users/{id}", produces = "application/json; charset=UTF-8")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
    @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.setViewName("user/login");
        return model;
    }
    @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("user/signup");
        return model;
    }
    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = null;
        try {
            userExists = userService.findUserByEmail(user.getEmail());
        }catch(Exception e){
        }
        if(userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("user/signup");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("user/login");
        }

        return model;
    }
    @RequestMapping(value= {"/home"}, method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.setViewName("home/home");
        return model;
    }
    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }


}

