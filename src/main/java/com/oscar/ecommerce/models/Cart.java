package com.oscar.ecommerce.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private SturshkUser sturshkUser;
    @OneToMany(mappedBy = "cart")
    private List<Product> products;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SturshkUser getSturshkUser() {
        return sturshkUser;
    }

    public void setSturshkUser(SturshkUser sturshkUser) {
        this.sturshkUser = sturshkUser;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", sturshkUser=" + sturshkUser +
                ", products=" + products +
                '}';
    }
}
