package com.java.pgs.wypozyczalnia.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Entity
@Table(name = "items")
public class Item {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Please provide item name")
    @Size(min=2, message="Name should have atleast 2 characters")
    private String name;

    private String description;

    @NotNull
    private int quantity;
    @ManyToMany(mappedBy = "items")
    private Collection<User> users;
}
