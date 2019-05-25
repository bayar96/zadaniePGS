package com.java.pgs.wypozyczalnia.domain;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "user_account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    @NotEmpty(message = "Please provide your first name")
    @Size(min=2, max=50)
    private String firstname;

    @Column(name = "lastname")
    @NotEmpty(message = "Please provide your last name")
    @Size(min=2, max=50)
    private String lastname;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty(message = "Please provide your email")
    @Size(min=5, max=100)
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Please provide your password")
    @Size(min=8, max=100)
    private String password;

    @Column(name = "active")
    private int active;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "users_rented_items", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))
    private Collection<Item> items;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles;








}
