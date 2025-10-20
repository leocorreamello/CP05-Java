package br.com.restaurante.financeiro.dto.account;

import br.com.restaurante.financeiro.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public class AccountCreateDTO {

    @NotBlank(message = "Account name is required")
    @Size(max = 50, message = "Account name must be at most 50 characters")
    private String name;

    @JsonProperty("type_name")
    @NotBlank(message = "Account type is required")
    private String typeName;

    @JsonProperty("payment_methods")
    private List<PaymentMethod> paymentMethods;

    @JsonProperty("initial_balance")
    private BigDecimal initialBalance;

    public AccountCreateDTO() {}

    public AccountCreateDTO(String name, String typeName, List<PaymentMethod> paymentMethods, BigDecimal initialBalance) {
        this.name = name;
        this.typeName = typeName;
        this.paymentMethods = paymentMethods;
        this.initialBalance = initialBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }
}
