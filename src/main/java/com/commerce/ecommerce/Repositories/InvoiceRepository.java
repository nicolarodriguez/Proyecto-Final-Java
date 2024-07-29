package com.commerce.ecommerce.Repositories;

import com.commerce.ecommerce.Entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByClientId(Long clientId);
    @Query("SELECT i FROM Invoice i WHERE i.client.id = :clientId ORDER BY i.issuedDate DESC")
    Optional<Invoice> findTopByClientIdOrderByIssuedDateDesc(@Param("clientId") Long clientId);
}
