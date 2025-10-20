package br.com.restaurante.financeiro.controllers;

import br.com.restaurante.financeiro.dto.transaction.TransactionCreateDTO;
import br.com.restaurante.financeiro.dto.transaction.TransactionResponseDTO;
import br.com.restaurante.financeiro.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("api/transactions")
@Validated
public class TransactionController {

    @Autowired
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody @Valid TransactionCreateDTO dto) {
        TransactionResponseDTO createdTransaction = transactionService.createTransaction(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTransaction.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdTransaction);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(
            @PathVariable Long id,
            @RequestBody @Valid TransactionCreateDTO dto) {
        TransactionResponseDTO updatedTransaction = transactionService.updateTransaction(id, dto);
        return ResponseEntity.ok(updatedTransaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable Long id) {
        TransactionResponseDTO transaction = transactionService.findTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

}
