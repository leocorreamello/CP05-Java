package br.com.restaurante.financeiro.entities;

import br.com.restaurante.financeiro.enums.AccountType;
import br.com.restaurante.financeiro.enums.PaymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private String name;

    @Column(name="TYPE", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @ElementCollection
    @CollectionTable(name = "account_payment_methods", joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    @Column(name="CURRENT_BALANCE", precision = 19, scale = 4)
    @NotNull
    private BigDecimal currentBalance = BigDecimal.ZERO;

    @Column(name="TRANSACTIONS")
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive = true;

    public Account() {}

    public Account(Long id, String name, AccountType type, List<PaymentMethod> paymentMethods, BigDecimal currentBalance, List<Transaction> transactions, boolean isActive) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.paymentMethods = paymentMethods;
        this.currentBalance = currentBalance;
        this.transactions = transactions;
        this.isActive = isActive;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public AccountType getType() { return type; }
    public void setType(AccountType type) { this.type = type; }

    public BigDecimal getCurrentBalance() { return currentBalance; }
    public void setCurrentBalance(BigDecimal currentBalance) { this.currentBalance = currentBalance; }

    public List<Transaction> getTransactions() { return transactions; }
    public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
