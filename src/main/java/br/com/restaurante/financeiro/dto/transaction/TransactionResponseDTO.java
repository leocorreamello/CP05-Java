package br.com.restaurante.financeiro.dto.transaction;

import br.com.restaurante.financeiro.enums.PaymentMethod;
import br.com.restaurante.financeiro.enums.TransactionType;

import java.math.BigDecimal;

public class TransactionResponseDTO {

    private Long id;
    private TransactionType type;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String categoryName;
    private String accountName;
    private String date;
    private String dueDate;
    private String description;

    public TransactionResponseDTO() {}

    public TransactionResponseDTO(Long id, TransactionType type, BigDecimal amount, PaymentMethod paymentMethod, String categoryName, String accountName, String date, String dueDate, String description) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.categoryName = categoryName;
        this.accountName = accountName;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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
