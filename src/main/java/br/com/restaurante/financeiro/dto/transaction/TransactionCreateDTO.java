package br.com.restaurante.financeiro.dto.transaction;

import br.com.restaurante.financeiro.enums.PaymentMethod;
import br.com.restaurante.financeiro.enums.TransactionCategory;
import br.com.restaurante.financeiro.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class TransactionCreateDTO {

    @NotNull(message = "Transaction type is required")
    private TransactionType type;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @JsonProperty("payment_method")
    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    @JsonProperty("category")
    @NotNull(message = "Category is required")
    private TransactionCategory category;

    @JsonProperty("account_name")
    @NotBlank(message = "Account name is required")
    private String accountName;

    @NotBlank(message = "Date is required")
    private String date;

    @JsonProperty("due_date")
    private String dueDate;

    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;

    public TransactionCreateDTO() {}

    public TransactionCreateDTO(TransactionType type, BigDecimal amount, PaymentMethod paymentMethod, TransactionCategory category, String accountName, String date, String dueDate, String description) {
        this.type = type;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.category = category;
        this.accountName = accountName;
        this.date = date;
        this.dueDate = dueDate;
        this.description = description;
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

    public TransactionCategory getCategory() { return category; }

    public void setCategory(TransactionCategory category) {
        this.category = category;
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
