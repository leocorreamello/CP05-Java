package br.com.restaurante.financeiro.controllers;

import br.com.restaurante.financeiro.dto.transaction.CategoryCreateDTO;
import br.com.restaurante.financeiro.dto.transaction.CategoryResponseDTO;
import br.com.restaurante.financeiro.dto.transaction.TransactionCreateDTO;
import br.com.restaurante.financeiro.dto.transaction.TransactionResponseDTO;
import br.com.restaurante.financeiro.enums.TransactionType;
import br.com.restaurante.financeiro.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/transactions")
@Validated
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

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

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseDTO> createTransactionCategory(@RequestBody @Valid CategoryCreateDTO dto) {
        CategoryResponseDTO createdCategory = transactionService.createTransactionCategory(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCategory.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdCategory);
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

    @GetMapping
    public List<TransactionResponseDTO> getTransactions(
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long accountId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,
            @RequestParam(required = false) String paymentMethod) {

        return transactionService.findTransactions(
                type, categoryId, accountId, startDate, endDate,
                minAmount, maxAmount, paymentMethod);
    }

}
