package br.com.restaurante.financeiro.dto.transaction;

import br.com.restaurante.financeiro.enums.PaymentMethod;
import br.com.restaurante.financeiro.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class TransactionUpdateDTO {

    private TransactionType type;

    @Positive(message = "Amount must be positive")
    private Double amount;

    @JsonProperty("payment_method")
    private PaymentMethod paymentMethod;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("account_name")
    private String accountName;

    private String date;

    @JsonProperty("due_date")
    private String dueDate;

    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;

    public TransactionUpdateDTO() {}

    public TransactionUpdateDTO(TransactionType type, Double amount, PaymentMethod paymentMethod, String categoryName, String accountName, String date, String dueDate, String description) {
        this.type = type;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.categoryName = categoryName;
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
