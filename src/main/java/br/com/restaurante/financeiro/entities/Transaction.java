package br.com.restaurante.financeiro.entities;

import br.com.restaurante.financeiro.enums.PaymentMethod;
import br.com.restaurante.financeiro.enums.TransactionCategory;
import br.com.restaurante.financeiro.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Column(name="ID_TRANSACTION")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TYPE", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "AMOUNT", nullable = false, precision = 19, scale = 4)
    @NotNull
    private Double amount;

    @Column(name = "PAYMENT_METHOD", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name="CATEGORY", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionCategory category;

    @Column(name="ACCOUNT", nullable = false)
    @NotNull
    @ManyToOne
    private Account account;

    @Column(name = "DATE", nullable = false)
    @NotNull
    private String date;

    @Column(name = "DUE_DATE")
    private String dueDate;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    public Transaction() {}

    public Transaction(Long id, TransactionType type, Double amount, PaymentMethod paymentMethod, Account account, TransactionCategory category, String date, String dueDate, String description) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.account = account;
        this.category = category;
        this.date = date;
        this.dueDate = dueDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public void setCategory(TransactionCategory category) {
        this.category = category;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
