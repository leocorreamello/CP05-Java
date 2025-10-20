package br.com.restaurante.financeiro.repositories;

import br.com.restaurante.financeiro.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByName(String name);
    public boolean existsByName(String name);

}
