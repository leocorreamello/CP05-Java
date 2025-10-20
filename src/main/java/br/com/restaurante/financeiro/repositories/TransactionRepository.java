package br.com.restaurante.financeiro.repositories;

import br.com.restaurante.financeiro.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
