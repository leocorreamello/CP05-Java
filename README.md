# Financeiro do Restaurante — Checkpoint 5 (Java)

Este projeto é uma API REST para gestão financeira de um restaurante, com cadastro de contas (Accounts) e lançamento de transações (Transactions), controle de saldo, validações de regras de negócio e tratamento centralizado de erros.

## Participantes
- Leonardo Correa - RM555573
- Pedro Guidotte - RM556630
- Gabriel Figueira - RM556476
- Herbert de Sousa - RM555701

## Como rodar (passo a passo simples)
1) Configure as credenciais do banco (opcional, recomendado) no Windows (cmd.exe):
```
set SPRING_DATASOURCE_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
set SPRING_DATASOURCE_USERNAME=seu_usuario
set SPRING_DATASOURCE_PASSWORD=sua_senha
```
2) Build e execução com Maven Wrapper:
```
.\mvnw.cmd clean install
.\mvnw.cmd spring-boot:run
```
3) A API sobe em: http://localhost:8080


## Por que essas escolhas (prós e contras)
- Spring Boot (Web, Validation, Data JPA)
  - Por quê: rapidez e padronização.
  - Prós: menos boilerplate, integração nativa com validação e JPA.
  - Contras: inicialização mais pesada que microframeworks.
- JPA/Hibernate
  - Por quê: ORM maduro e transações simples.
  - Prós: produtividade, queries automáticas.
  - Contras: tuning e atenção a lazy/eager.
- Oracle Database
  - Por quê: alinhado ao ambiente do projeto.
  - Prós: robustez corporativa.
  - Contras: setup local mais complexo (para DEV, H2 seria mais simples).
- DTO + Bean Validation
  - Por quê: contratos estáveis e validação antecipada.
  - Prós: mensagens claras; evita expor entidades.
  - Contras: mapeamentos extras.
- Handler global de erros
  - Por quê: respostas padronizadas.
  - Prós: consistência de códigos/status.
  - Contras: precisa lançar exceções corretas.


## Códigos de retorno (onde estão)
- a) Todas as APIs (controllers)
  - POST /api/accounts, /api/transactions → 201 Created (+ Location)
  - GET /{id}, PATCH /{id} → 200 OK
  - DELETE /{id} → 204 No Content
- b) Validações de negócio (services)
  - Regras violadas → lançar AccountException/TransactionException → 400 Bad Request
  - Recurso inexistente → lançar EntityNotFoundException → 404 Not Found
- c) Erro de aplicação (GlobalExceptionHandler)
  - 400: MethodArgumentNotValid, IllegalArgument, TypeMismatch
  - 404: EntityNotFound, NoResourceFound
  - 500: Exceção não mapeada
- d) Outros necessários
  - Validação de DTO (Bean Validation) → 400 com detalhes dos campos
  - Enums/formatos inválidos → 400 (IllegalArgumentException)
  - Caminho inexistente → 404


