package br.com.restaurante.financeiro.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="account_types")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_ACCOUNT_TYPE")
    private Long id;

    @Column(name="TYPE_NAME", nullable = false, length = 50)
    @NotBlank
    @Size(max = 50, message = "O nome do tipo de conta deve ter no máximo 50 caracteres.")
    private String name;

    public AccountType() {}
    public AccountType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
