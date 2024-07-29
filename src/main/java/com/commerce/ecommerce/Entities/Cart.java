package com.commerce.ecommerce.Entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Schema(description = "Entity of Cart")
public class Cart {

    @Schema(description = "Cart identifier", example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column private Long id;

    @OneToOne
    @Getter @Setter private Client client;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "List of cartItems")
    @Getter @Setter @Column private List<CartItems> cartItems = new ArrayList<>();
}