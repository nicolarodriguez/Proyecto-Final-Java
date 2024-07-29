package com.commerce.ecommerce.Controllers;

import com.commerce.ecommerce.Entities.Invoice;
import com.commerce.ecommerce.Services.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoices")
@Tag(name = "Route of invoices", description = "CRUD of invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/{clientId}")
    @Operation(summary = "Create an invoice", description = "")
    public ResponseEntity<Invoice> createInvoice(@PathVariable Long clientId){
        Invoice invoice = invoiceService.createInvoice(clientId);
        return ResponseEntity.ok(invoice);
    }
    @GetMapping("/{clientId}")
    @Operation(summary = "Read invoice", description = "Read a client invoice by id")
    public ResponseEntity<Invoice> readInvoice(@PathVariable Long clientId){
        Invoice invoice = invoiceService.readInvoice(clientId);
        return ResponseEntity.ok(invoice);
    }
}
