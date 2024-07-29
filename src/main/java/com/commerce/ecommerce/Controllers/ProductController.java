package com.commerce.ecommerce.Controllers;


import com.commerce.ecommerce.Entities.Product;
import com.commerce.ecommerce.Services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Route of products", description = "CRUD of products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @Operation(summary = "Create a product", description = "Requires product data")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
    @GetMapping
    @Operation(summary = "Read products", description = "Read all products created")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Read a product", description = "Read a product by id")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    @Operation(summary = "Modify a product", description = "Modify a product by id")
    public Optional<Product> modifyProduct(@PathVariable Long id, @RequestBody Product productDetails){
        return productService.modifyProduct(id, productDetails);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Delete a product already created by id")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id){
        try{
            productService.deleteProductById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
