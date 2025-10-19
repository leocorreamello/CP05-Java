package br.com.restaurante.financeiro.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="accounts")
public class Account {

    @Column(name="ID_ACCOUNT")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME", nullable = false, length = 50)
    @NotBlank
    @Size(max = 50, message = "O nome da conta deve ter no máximo 50 caracteres.")
    private String name;

    @Column(name="TYPE", nullable = false)
    @NotBlank
    @ManyToOne
    private AccountType type;

    @Column(name="CURRENT_BALANCE", precision = 19, scale = 4)
    @NotNull
    @Digits(integer = 15, fraction = 4, message = "O saldo atual deve ter no máximo 15 dígitos inteiros e 4 dígitos decimais.")
    private BigDecimal currentBalance = BigDecimal.ZERO;

    @Column(name="TRANSACTIONS")
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FinancialTransaction> transactions;

    public Account() {}

    public Account(BigDecimal currentBalance, AccountType type, String name, Long id) {
        this.currentBalance = currentBalance;
        this.type = type;
        this.name = name;
        this.id = id;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public AccountType getType() { return type; }
    public void setType(AccountType type) { this.type = type; }

    public BigDecimal getCurrentBalance() { return currentBalance; }
    public void setCurrentBalance(BigDecimal currentBalance) { this.currentBalance = currentBalance; }

    public List<FinancialTransaction> getTransactions() { return transactions; }
    public void setTransactions(List<FinancialTransaction> transactions) { this.transactions = transactions; }
}
