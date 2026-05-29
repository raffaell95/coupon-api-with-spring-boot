# Coupon API

API REST para gerenciamento de cupons desenvolvida com Java e Spring Boot, utilizando princípios de Clean Architecture e encapsulamento das regras de negócio no domínio.

---

# Tecnologias Utilizadas

* Java 25+
* Spring Boot
* Spring Web
* Spring Data JPA
* H2 Database
* Swagger OpenAPI
* JUnit 5
* Mockito
* Docker
* Docker Compose
* Maven

---

# Arquitetura

O projeto segue os princípios de Clean Architecture.

```txt
src/main/java/com/raffa/coupon
│
├── api
│   ├── controller
│   └── handler  
│
├── application
│   ├── dto
│   ├── mapper
│   └── service
│
├── domain
│   ├── exception
│   ├── model
│   └── repository  
│
└── infrastructure
    ├── adapter
    ├── config
    └── persistence
        ├── entity
        └── repository
```

---

# Regras de Negócio

## Criação de Cupom

Um cupom deve possuir obrigatoriamente:

* code
* description
* discountValue
* expirationDate

### Regras aplicadas

* O código do cupom é alfanumérico.
* Caracteres especiais são removidos automaticamente.
* O código possui tamanho máximo de 6 caracteres.
* O desconto mínimo permitido é `0.5`.
* O cupom não pode possuir data de expiração no passado.
* O cupom pode ser criado já publicado.

---

## Exclusão de Cupom

* Exclusão lógica (soft delete).
* O cupom não é removido fisicamente do banco.
* Não é possível deletar um cupom já deletado.

---

# Como Executar o Projeto

## Executar Localmente

### 1. Clonar repositório

```bash
git clone https://github.com/raffaell95/coupon-api.git
```

---

### 2. Entrar na pasta do projeto

```bash
cd coupon-api
```

---

### 3. Executar aplicação

```bash
mvn spring-boot:run
```

---

# Executar com Docker

## 1. Gerar build do projeto

```bash
mvn clean package
```

---

## 2. Subir aplicação

```bash
docker compose up --build
```

---

# Swagger

Documentação da API disponível em:

```txt
http://localhost:8080/swagger-ui/index.html
```

---

# H2 Console

Console do banco H2:

```txt
http://localhost:8080/h2-console
```

## Configuração H2

| Campo     | Valor       |
| --------- | ----------- |
| JDBC URL  | jdbc:h2:mem |
| User Name | sa          |
| Password  | vazio       |

---

# Actuator Health Check

Endpoint de health check:

```txt
http://localhost:8080/actuator/health
```

---

# Endpoints

# Criar Cupom

## Request

```http
POST /coupons
Content-Type: application/json
```

### Body

```json
{
  "code": "AB@12#",
  "description": "Black Friday",
  "discountValue": 10.5,
  "expirationDate": "2026-12-31",
  "published": true
}
```

---

## Response

### Status: 201 Created

```json
{
  "id": "c0a80121-7ac0-4d5f-9fd5-65ec7c3d7d21",
  "code": "AB12",
  "description": "Black Friday",
  "discountValue": 10.5,
  "expirationDate": "2026-12-31",
  "published": true,
  "deleted": false
}
```

---

## Response de Erro

### Status: 400 Bad Request

```json
{
  "message": "Coupon expiration date cannot be in the past"
}
```

---

# Deletar Cupom

## Request

```http
DELETE /coupons/{id}
```

### Example

```http
DELETE /coupons/c0a80121-7ac0-4d5f-9fd5-65ec7c3d7d21
```

---

## Response

### Status: 204 No Content

```txt
No Content
```

---

## Response de Erro

### Status: 404 Not Found

```json
{
  "message": "Coupon not found"
}
```

---

### Status: 400 Bad Request

```json
{
  "message": "Coupon already deleted"
}
```

---

# Executando os Testes

```bash
mvn test
```

---

# Cobertura de Testes

O projeto possui cobertura de: 93%


<img width="1916" height="1017" alt="Captura de tela 2026-05-28 235811" src="https://github.com/user-attachments/assets/90b6294a-9a14-4bda-85db-e1cc9126057f" />

---

# Conceitos Aplicados

* Clean Architecture
* SOLID
* Encapsulamento
* Value Objects
* Soft Delete
* DTO Pattern
* Repository Pattern
* Adapter Pattern
* Exception Handling

---

# Autor

Rafael Cunha Ribeiro

* GitHub: https://github.com/raffaell95
* LinkedIn: https://www.linkedin.com/in/rafaelcunharibeiro/
