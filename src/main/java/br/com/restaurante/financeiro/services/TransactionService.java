package br.com.restaurante.financeiro.services;

import br.com.restaurante.financeiro.dto.transaction.TransactionCreateDTO;
import br.com.restaurante.financeiro.dto.transaction.TransactionResponseDTO;
import br.com.restaurante.financeiro.entities.Account;
import br.com.restaurante.financeiro.entities.Transaction;
import br.com.restaurante.financeiro.enums.TransactionType;
import br.com.restaurante.financeiro.exceptions.TransactionException;
import br.com.restaurante.financeiro.repositories.AccountRepository;
import br.com.restaurante.financeiro.repositories.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public TransactionResponseDTO createTransaction(@Valid TransactionCreateDTO dto) {

        Account account = accountRepository.findByName(dto.getAccountName())
                 .orElseThrow(() -> new TransactionException("Conta não encontrada: " + dto.getAccountName()));

        if (!account.getActive()) {
            throw new TransactionException("Conta inativa: " + dto.getAccountName());
        }

        Transaction transaction = new Transaction();
            transaction.setType(dto.getType());
            transaction.setAmount(dto.getAmount());
            transaction.setPaymentMethod(dto.getPaymentMethod());
            transaction.setCategory(dto.getCategory());
            transaction.setAccount(account);
            transaction.setDate(dto.getDate());
            transaction.setDueDate(dto.getDueDate());
            transaction.setDescription(dto.getDescription());

        BigDecimal currentBalance = account.getCurrentBalance();
        BigDecimal transactionAmount = dto.getAmount();

        if (dto.getType() == TransactionType.INCOME) {
            account.setCurrentBalance(currentBalance.add(transactionAmount));
        } else if (dto.getType() == TransactionType.EXPENSE) {
            account.setCurrentBalance(currentBalance.subtract(transactionAmount));
        }

        accountRepository.save(account);
        Transaction savedTransaction = transactionRepository.save(transaction);

        return convertToResponseDTO(savedTransaction);
    }


    public TransactionResponseDTO findTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                 .orElseThrow(() -> new TransactionException("Transação não encontrada com ID: " + id));
        return convertToResponseDTO(transaction);
    }

    @Transactional
    public TransactionResponseDTO updateTransaction(Long id, @Valid TransactionCreateDTO dto) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionException("Transação não encontrada com ID: " + id));

        Account newAccount = accountRepository.findAll().stream()
                .filter(a -> a.getName().equals(dto.getAccountName()))
                .findFirst()
                .orElseThrow(() -> new TransactionException("Conta não encontrada: " + dto.getAccountName()));

        if (!newAccount.getActive()) {
            throw new TransactionException("Conta inativa: " + dto.getAccountName());
        }

        Account account = transaction.getAccount();
        BigDecimal oldAmount = transaction.getAmount();

        if (transaction.getType() == TransactionType.INCOME) {
            account.setCurrentBalance(account.getCurrentBalance().subtract(oldAmount));
        } else if (transaction.getType() == TransactionType.EXPENSE) {
            account.setCurrentBalance(account.getCurrentBalance().add(oldAmount));
        }

        transaction.setType(dto.getType());
        transaction.setAmount(dto.getAmount());
        transaction.setPaymentMethod(dto.getPaymentMethod());
        transaction.setCategory(dto.getCategory());
        transaction.setAccount(newAccount);
        transaction.setDate(dto.getDate());
        transaction.setDueDate(dto.getDueDate());
        transaction.setDescription(dto.getDescription());

        BigDecimal newAmount = dto.getAmount();

        if (dto.getType() == TransactionType.INCOME) {
            newAccount.setCurrentBalance(newAccount.getCurrentBalance().add(newAmount));
        } else if (dto.getType() == TransactionType.EXPENSE) {
            newAccount.setCurrentBalance(newAccount.getCurrentBalance().subtract(newAmount));
        }

        if (!account.getId().equals(newAccount.getId())) {
            accountRepository.save(account);
        }

        accountRepository.save(newAccount);

        Transaction updatedTransaction = transactionRepository.save(transaction);

        return convertToResponseDTO(updatedTransaction);
    }

    @Transactional
    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionException("Transação não encontrada com ID: " + id));

        Account account = transaction.getAccount();
        BigDecimal amount = transaction.getAmount();

        if (transaction.getType() == TransactionType.INCOME) {
            account.setCurrentBalance(account.getCurrentBalance().subtract(amount));
        } else if (transaction.getType() == TransactionType.EXPENSE) {
            account.setCurrentBalance(account.getCurrentBalance().add(amount));
        }

        accountRepository.save(account);

        transactionRepository.delete(transaction);
    }

    private TransactionResponseDTO convertToResponseDTO(Transaction transaction) {
        return new TransactionResponseDTO(
                transaction.getId(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getPaymentMethod(),
                transaction.getCategory().name(),
                transaction.getAccount().getName(),
                transaction.getDate(),
                transaction.getDueDate(),
                transaction.getDescription()
        );
    }

}
