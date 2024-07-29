package com.commerce.ecommerce.Repositories;

import com.commerce.ecommerce.Entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByClientId(Long clientId);
}
