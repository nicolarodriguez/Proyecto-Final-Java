package com.commerce.ecommerce.Services;

import com.commerce.ecommerce.Entities.Cart;
import com.commerce.ecommerce.Entities.CartItems;
import com.commerce.ecommerce.Entities.Invoice;
import com.commerce.ecommerce.Repositories.CartRepository;
import com.commerce.ecommerce.Repositories.ClientRepository;
import com.commerce.ecommerce.Repositories.InvoiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Transactional
    public Invoice createInvoice(Long clientId){
        Cart cart = cartRepository.findByClientId(clientId);
        if (cart == null){
            throw new RuntimeException("El carrito con id"+ clientId +"no existe");
        }
        List<CartItems> itemsToInvoice = cart.getCartItems().stream().filter(item -> !item.isDelivered()).collect(Collectors.toList());

        if (itemsToInvoice.isEmpty()){
            throw new RuntimeException("No hay productos entregados en el carrito");
        }
        double total = itemsToInvoice.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getAmount()).sum();

        Invoice invoice = new Invoice();
        invoice.setClient(cart.getClient());
        invoice.setTotal(total);
        invoice.setItems(new HashSet<>(itemsToInvoice));

        invoiceRepository.save(invoice);

        itemsToInvoice.forEach(item -> item.setDelivered(true));
        cartRepository.save(cart);

        return invoice;
    }
    public Invoice readInvoice(Long clientId){
        return invoiceRepository.findTopByClientIdOrderByIssuedDateDesc(clientId).orElseThrow(()-> new RuntimeException("No se encontro ningun comprobante"));
    }
}
