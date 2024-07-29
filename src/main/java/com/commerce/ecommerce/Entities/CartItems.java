package com.commerce.ecommerce.Entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Schema(description = "Entity in cart")
public class CartItems {

    @Schema(description = "CartItems identifier", example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column private Long id;

    @Schema(description = "Amount of items in cart", example = "50")
    @Getter @Setter @Column private Integer amount;
    @Schema(description = "Items delivered", example = "False")
    @Getter @Setter @Column private boolean delivered;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Getter @Setter private Product product;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @Getter @Setter private Invoice invoice;

    public boolean idDelivered(){
        return delivered;
    }
}
