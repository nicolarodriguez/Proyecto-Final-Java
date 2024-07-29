package com.commerce.ecommerce.Services;

import com.commerce.ecommerce.Entities.Cart;
import com.commerce.ecommerce.Entities.CartItems;
import com.commerce.ecommerce.Entities.Product;
import com.commerce.ecommerce.Repositories.CartRepository;
import com.commerce.ecommerce.Repositories.ClientRepository;
import com.commerce.ecommerce.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;

    public Cart getCartById(Long clientId){
        return cartRepository.findByClientId(clientId);
    }
    public Cart addProductToCart(Long clientId, Long productId, Integer amount){
        Cart cart = cartRepository.findByClientId(clientId);
        if (cart == null){
            cart = new Cart();
            cart.setClient(clientRepository.findById(clientId).orElseThrow());
            cartRepository.save(cart);
        }
        Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Producto no encontrado con ID:" + productId));
        Optional<CartItems> existingItem = cart.getCartItems().stream().filter(item-> item.getProduct().getId().equals(productId)).findFirst();

        if (existingItem.isPresent()){
            CartItems item = existingItem.get();
            item.setAmount(item.getAmount() + amount);
        } else {
            CartItems item = new CartItems();
            item.setProduct(product);
            item.setAmount(amount);
            item.setDelivered(false);
            cart.getCartItems().add(item);
        }
        return cartRepository.save(cart);
    }
    public Cart removeFromCart(Long clientId, Long productId, Integer amount){
        Cart cart = cartRepository.findByClientId(clientId);
        if (cart != null){
            cart.getCartItems().removeIf(item-> {
                if (item.getProduct().getId().equals(productId)) {
                    if (item.getAmount() > amount) {
                        item.setAmount(item.getAmount() - amount);
                        return false;
                    } else if (item.getAmount().equals(amount)) {
                        return true;
                    }
                }
                return false;
            });
            return cartRepository.save(cart);
        }
        return null;
    }
    public  double createInvoice(Long clientId){
        Cart cart = cartRepository.findByClientId(clientId);
        if (cart == null){
            throw new RuntimeException("No se encontro el carrito con id " + clientId);
        }
        return cart.getCartItems().stream().mapToDouble(item-> item.getProduct().getPrice() * item.getAmount()).sum();
    }
}
