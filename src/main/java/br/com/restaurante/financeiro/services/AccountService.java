package br.com.restaurante.financeiro.services;

import br.com.restaurante.financeiro.dto.account.AccountCreateDTO;
import br.com.restaurante.financeiro.dto.account.AccountResponseDTO;
import br.com.restaurante.financeiro.entities.Account;
import br.com.restaurante.financeiro.enums.AccountType;
import br.com.restaurante.financeiro.exceptions.AccountException;
import br.com.restaurante.financeiro.repositories.AccountRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public AccountResponseDTO createAccount(@Valid AccountCreateDTO dto) {
        // Validar se já existe conta com o mesmo nome
        if (accountRepository.existsByName(dto.getName())) {
            throw new AccountException("Já existe uma conta com o nome: " + dto.getName());
        }

        // Converter o tipo de String para Enum
        AccountType accountType;
        try {
            accountType = AccountType.valueOf(dto.getTypeName().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AccountException("Tipo de conta inválido: " + dto.getTypeName());
        }

        // Criar a conta
        Account account = new Account();
        account.setName(dto.getName());
        account.setType(accountType);
        account.setPaymentMethods(dto.getPaymentMethods() != null ? dto.getPaymentMethods() : new ArrayList<>());
        account.setCurrentBalance(dto.getInitialBalance() != null ? dto.getInitialBalance() : BigDecimal.ZERO);
        account.setActive(true);

        // Salvar a conta
        Account savedAccount = accountRepository.save(account);

        // Converter para DTO de resposta
        return convertToResponseDTO(savedAccount);
    }

    public AccountResponseDTO findAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("Conta não encontrada com ID: " + id));
        return convertToResponseDTO(account);
    }

    public List<AccountResponseDTO> findAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<AccountResponseDTO> findActiveAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .filter(Account::getActive)
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AccountResponseDTO updateAccount(Long id, @Valid AccountCreateDTO dto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("Conta não encontrada com ID: " + id));

        // Validar se o novo nome já existe em outra conta
        if (!account.getName().equals(dto.getName()) && accountRepository.existsByName(dto.getName())) {
            throw new AccountException("Já existe uma conta com o nome: " + dto.getName());
        }

        // Converter o tipo de String para Enum
        AccountType accountType;
        try {
            accountType = AccountType.valueOf(dto.getTypeName().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AccountException("Tipo de conta inválido: " + dto.getTypeName());
        }

        // Atualizar os campos
        account.setName(dto.getName());
        account.setType(accountType);
        if (dto.getPaymentMethods() != null) {
            account.setPaymentMethods(dto.getPaymentMethods());
        }

        // Salvar as alterações
        Account updatedAccount = accountRepository.save(account);
        return convertToResponseDTO(updatedAccount);
    }

    @Transactional
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("Conta não encontrada com ID: " + id));

        // Soft delete - apenas desativa a conta
        account.setActive(false);
        accountRepository.save(account);
    }

    private AccountResponseDTO convertToResponseDTO(Account account) {
        return new AccountResponseDTO(
                account.getId(),
                account.getName(),
                account.getType().name(),
                account.getPaymentMethods(),
                account.getCurrentBalance(),
                account.getCreatedAt(),
                account.getUpdatedAt(),
                account.getActive()
        );
    }
}
