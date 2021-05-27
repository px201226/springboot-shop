package com.alethio.service.service.domain.item;

import javax.persistence.*;


@Entity(name = "clothes")
public class Clothes  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long stockQuantity;

    private String productName;

}
