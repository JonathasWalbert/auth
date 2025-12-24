# Auth API

API de autenticação construída com **Java 21** e **Spring Boot**, organizada em camadas (Controller → Domínio → Infra), com autenticação **stateless via JWT** e persistência em **MySQL**.

## 📌 Visão Geral da Arquitetura

- **Controller**: expõe os endpoints REST de login e registro.
- **Domínio**: regras de negócio, entidade `User`, validações e serviço de autenticação.
- **Infra/Security**: configuração do Spring Security, filtro JWT e serviço de geração/validação do token.

### Fluxo de Autenticação (JWT)

1. Login recebe email/senha.
2. Spring Security autentica o usuário.
3. Token JWT é gerado e retornado.
4. Requests autenticadas enviam `Authorization: Bearer <token>`.
5. Filtro valida o token e popula o contexto de segurança.

## 🧰 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 4**
- **Spring Web MVC**
- **Spring Security** (stateless + JWT)
- **Spring Data JPA**
- **MySQL**
- **Flyway** (migrações)
- **Bean Validation**
- **Lombok**
- **Maven**
- **JWT (Auth0 java-jwt)**

## 📂 Estrutura de Pastas (resumo)

```
src/main/java/com/authentication/auth
├── controller
├── domain
│   └── user
└── infra
    └── security
```

## 🚀 Como Executar

1. **Configure o banco MySQL**
   - Crie o schema `authentication`.
   - Ajuste usuário/senha em `src/main/resources/application.properties`.

2. **Defina o segredo do JWT (opcional)**
   - Variável de ambiente: `JWT_SECRET`
   - Ou altere em `application.properties`.

3. **Rode a aplicação**

```bash
./mvnw spring-boot:run
```

## 🔐 Endpoints

### POST `/authentication/register`
Cria um novo usuário.

**Body:**
```json
{
  "name": "Fulano",
  "lastname": "Silva",
  "email": "fulano@email.com",
  "password": "123456"
}
```

### POST `/authentication/login`
Autentica e retorna o token JWT.

**Body:**
```json
{
  "email": "fulano@email.com",
  "password": "123456"
}
```

**Response:**
```json
{
  "token": "<jwt>"
}
```

## ✅ Observações

- A aplicação é **stateless**, não usa sessão.
- Senhas são armazenadas com **BCrypt**.
- Migração inicial cria a tabela `usuarios` automaticamente via Flyway.

---

Feito com ❤️ usando Spring Boot.
