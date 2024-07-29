package com.commerce.ecommerce.Entities;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor @ToString @EqualsAndHashCode @Getter @Setter
@Schema(description = "Entity of client")
public class Client {

    @Schema(description = "Invoice identifier", example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Schema(description = "Client name", example = "Jarad")
    @Getter @Setter private String name;
    @Schema(description = "Client lastname", example = "Higgins")
    @Getter @Setter private String lastname;
    @Schema(description = "Client dni", example = "1891")
    @Getter @Setter private Integer dni;
}
