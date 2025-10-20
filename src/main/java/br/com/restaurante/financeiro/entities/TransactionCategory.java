package br.com.restaurante.financeiro.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "transaction_categories")
public class TransactionCategory {

    @Column(name="ID_TRANSACTION_CATEGORY")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="CATEGORY_NAME", nullable = false, length = 50)
    @NotBlank
    private String name;

    public TransactionCategory() {}

    public TransactionCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
