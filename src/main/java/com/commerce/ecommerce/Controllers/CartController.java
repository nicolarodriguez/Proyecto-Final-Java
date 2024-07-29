package com.commerce.ecommerce.Controllers;

import com.commerce.ecommerce.Entities.Cart;
import com.commerce.ecommerce.Repositories.ClientRepository;
import com.commerce.ecommerce.Services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.*;

import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/carts")
@Tag(name = "Route of carts", description = "CRUD of carts")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/{clientId}")
    @Operation(summary = "Read cart", description = "Read all products from cart")
    public ResponseEntity<Cart> getAllFromCart(@PathVariable Long clientId){
        Cart cart = cartService.getCartById(clientId);

        if (cart != null){
            cart.setCartItems(cart.getCartItems().stream().filter(item -> !item.isDelivered()).collect(Collectors.toList()));
        }
        return ResponseEntity.ok(cart);
    }
    @PostMapping("/{clientId}/{productId}/{amount}")
    @Operation(summary = "Add a product to cart", description = "Add a product to the cart, requires clientId, productId and amount")
    public ResponseEntity<Cart> addToCart(@PathVariable Long clientId, @PathVariable Long productId, @PathVariable int amount){
        Cart cart = cartService.addProductToCart(clientId, productId, amount);
        return ResponseEntity.ok(cart);
    }
    @DeleteMapping("/{clientId}")
    @Operation(summary = "Remove from cart", description = "Remove a product from cart, requires clientId, productId and amount")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Long clientId, @RequestBody ProductRequest request) {
        Cart cart = cartService.removeFromCart(clientId, request.getProductId(), request.getAmount());
        return ResponseEntity.ok(cart);
    }
    public static class ProductRequest{
        private Long productId;
        private Integer amount;

        public Long getProductId(){
            return productId;
        }
        public void setProductId(Long productId){
            this.productId = productId;
        }
        public Integer getAmount(){
            return amount;
        }
        public void setAmount(Integer amount){
            this.amount = amount;
        }
    }
}
