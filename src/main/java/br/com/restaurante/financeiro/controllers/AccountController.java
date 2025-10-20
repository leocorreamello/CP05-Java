package br.com.restaurante.financeiro.controllers;

import br.com.restaurante.financeiro.dto.account.AccountCreateDTO;
import br.com.restaurante.financeiro.dto.account.AccountResponseDTO;
import br.com.restaurante.financeiro.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/accounts")
@Validated
public class AccountController {

    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody @Valid AccountCreateDTO dto) {
        AccountResponseDTO createdAccount = accountService.createAccount(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAccount.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdAccount);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> updateAccount(
            @PathVariable Long id,
            @RequestBody @Valid AccountCreateDTO dto) {
        AccountResponseDTO updatedAccount = accountService.updateAccount(id, dto);
        return ResponseEntity.ok(updatedAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {
        AccountResponseDTO account = accountService.findAccountById(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts(
            @RequestParam(required = false, defaultValue = "false") Boolean activeOnly) {
        List<AccountResponseDTO> accounts = activeOnly
                ? accountService.findActiveAccounts()
                : accountService.findAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
