package com.commerce.ecommerce.Entities;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor @ToString @EqualsAndHashCode
@Schema(description = "Entity of product")
public class Product {

    @Schema(description = "Product identifier", example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Schema(description = "Product name", example = "Remera")
    @Getter @Setter private String name;
    @Schema(description = "Product price", example = "200")
    @Getter @Setter private Long price;
}
