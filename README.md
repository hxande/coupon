# 🎟️ Coupon API

API REST para gerenciamento de cupons.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database (in-memory)
- Docker
- Docker Compose
- Swagger (OpenAPI)

---

## 📌 Regras de Negócio

### ✔ Create

- Um cupom pode ser cadastrado a qualquer momento.
- Campos obrigatórios:
    - `code`
    - `description`
    - `discountValue`
    - `expirationDate`
- O código:
    - Deve possuir **6 caracteres alfanuméricos**
    - Caracteres especiais são removidos automaticamente
    - É normalizado para letras maiúsculas
- O valor de desconto:
    - Mínimo: `0.5`
    - Sem limite máximo
- A data de expiração:
    - Não pode estar no passado
- Pode ser criado como publicado

---

### ✔ Delete

- Soft delete
- Não remove fisicamente do banco
- Apenas altera o status para `DELETED`
- Não permite deletar novamente

---
