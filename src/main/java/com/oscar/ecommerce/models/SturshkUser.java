package com.oscar.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class SturshkUser {
    @Id
    private String auth0Id;
    private String email;
    private String name;
    private String role;
}
