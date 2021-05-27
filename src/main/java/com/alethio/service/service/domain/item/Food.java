package com.alethio.service.service.domain.item;


import javax.persistence.*;

@Entity(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long stockQuantity;

    private String productName;
}
