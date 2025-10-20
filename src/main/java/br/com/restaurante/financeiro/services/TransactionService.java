package br.com.restaurante.financeiro.services;

import br.com.restaurante.financeiro.dto.transaction.CategoryCreateDTO;
import br.com.restaurante.financeiro.dto.transaction.CategoryResponseDTO;
import br.com.restaurante.financeiro.dto.transaction.TransactionCreateDTO;
import br.com.restaurante.financeiro.dto.transaction.TransactionResponseDTO;
import br.com.restaurante.financeiro.entities.Transaction;
import br.com.restaurante.financeiro.enums.TransactionType;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    // TODO: Injetar os repositórios quando forem criados
    // @Autowired
    // private TransactionRepository transactionRepository;
    // @Autowired
    // private TransactionCategoryRepository categoryRepository;
    // @Autowired
    // private AccountRepository accountRepository;

    public TransactionResponseDTO createTransaction(@Valid TransactionCreateDTO dto) {
        // TODO: Implementar a lógica completa quando os repositórios estiverem prontos
        // 1. Buscar a categoria pelo nome (ou criar se não existir)
        // TransactionCategory category = categoryRepository.findByName(dto.getCategoryName())
        //         .orElseGet(() -> categoryRepository.save(new TransactionCategory(null, dto.getCategoryName())));

        // 2. Buscar a conta pelo nome
        // Account account = accountRepository.findByName(dto.getAccountName())
        //         .orElseThrow(() -> new RuntimeException("Conta não encontrada: " + dto.getAccountName()));

        // 3. Criar a transação
        // Transaction transaction = new Transaction();
        // transaction.setType(dto.getType());
        // transaction.setAmount(dto.getAmount());
        // transaction.setPaymentMethod(dto.getPaymentMethod());
        // transaction.setCategory(category);
        // transaction.setAccount(account);
        // transaction.setDate(dto.getDate());
        // transaction.setDueDate(dto.getDueDate());
        // transaction.setDescription(dto.getDescription());

        // 4. Salvar a transação
        // Transaction savedTransaction = transactionRepository.save(transaction);

        // 5. Converter para DTO de resposta
        // return convertToResponseDTO(savedTransaction);

        // Placeholder para compilar
        return new TransactionResponseDTO();
    }

    public CategoryResponseDTO createTransactionCategory(@Valid CategoryCreateDTO dto) {
        // TODO: Implementar quando o repositório estiver pronto
        // TransactionCategory category = new TransactionCategory();
        // category.setName(dto.getName());
        // TransactionCategory savedCategory = categoryRepository.save(category);
        // return new CategoryResponseDTO(savedCategory.getId(), savedCategory.getName());

        // Placeholder para compilar
        return new CategoryResponseDTO();
    }

    public TransactionResponseDTO findTransactionById(Long id) {
        // TODO: Implementar quando o repositório estiver pronto
        // Transaction transaction = transactionRepository.findById(id)
        //         .orElseThrow(() -> new RuntimeException("Transação não encontrada com ID: " + id));
        // return convertToResponseDTO(transaction);

        // Placeholder para compilar
        return new TransactionResponseDTO();
    }

    public List<TransactionResponseDTO> findTransactions(TransactionType type, Long categoryId, Long accountId,
                                                          String startDate, String endDate, Double minAmount,
                                                          Double maxAmount, String paymentMethod) {
        // TODO: Implementar a lógica de filtros quando o repositório estiver pronto
        // List<Transaction> transactions = transactionRepository.findAll(); // ou usar Specification para filtros
        // return transactions.stream()
        //         .map(this::convertToResponseDTO)
        //         .collect(Collectors.toList());

        // Placeholder para compilar
        return List.of();
    }

    private TransactionResponseDTO convertToResponseDTO(Transaction transaction) {
        return new TransactionResponseDTO(
                transaction.getId(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getPaymentMethod(),
                transaction.getCategory().getName(),
                transaction.getAccount().getName(),
                transaction.getDate(),
                transaction.getDueDate(),
                transaction.getDescription()
        );
    }

    public TransactionResponseDTO updateTransaction(Long id, @Valid TransactionCreateDTO dto) {
        return null;
    }
}
