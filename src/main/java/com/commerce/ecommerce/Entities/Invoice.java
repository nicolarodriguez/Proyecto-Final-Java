package com.commerce.ecommerce.Entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor @ToString @EqualsAndHashCode
@Schema(description = "Entity of invoice")
public class Invoice {

    @Schema(description = "Invoice identifier", example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column private Long id;

    @Schema(description = "Total price in invoice", example = "1500")
    @Getter @Setter private double total;
    @Schema(description = "Invoice creation date", example = "26/7/24")
    @Getter @Setter private LocalDateTime issuedDate;

    @OneToOne
    @Getter @Setter private Client client;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    @Getter @Setter private Set<CartItems> items;

    @PrePersist
    protected void onCreate(){
        this.issuedDate = LocalDateTime.now();
    }
}
