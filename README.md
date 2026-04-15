
# 📚 Terminux Review API

API REST para gerenciamento de reviews de livros, desenvolvida com Java e Spring Boot, aplicando princípios de Clean Architecture inspirados em DDD.

O projeto foi construído com foco em boas práticas de engenharia de software, separação de responsabilidades, validações robustas e organização de código voltada para escalabilidade e manutenção.

---

## 🚀 Tecnologias

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Bean Validation (Jakarta Validation)

---

## 🧠 Arquitetura

O projeto segue uma arquitetura inspirada em Clean Architecture, com separação clara de responsabilidades:

Controller → Service → Domain → Repository → Adapter → Database

### 🔹 Camadas

- **Controller** → entrada HTTP (REST)
- **Service** → regras de negócio
- **Domain** → modelo de domínio + Value Objects
- **Repository (Domain)** → abstração de persistência
- **Adapter (Infra)** → implementação com Spring Data JPA
- **Mapper** → conversão entre camadas

---

## 🧱 Modelagem de Domínio

### Entidade principal:
- `Review`

### Value Objects:
- `UserName`
- `BookTitle`
- `Rating`
- `ReviewText`

### Regras importantes:
- ID gerado pelo banco (não pelo domínio)
- `userName` não pode ser alterado
- `rating` é obrigatório (0–10)
- `reviewText` possui tamanho mínimo e máximo
- validações aplicadas no DTO e no domínio

---

## 📦 Endpoints

| Método | Endpoint | Descrição |
|--------|--------|----------|
| GET | `/reviews` | Lista todas as reviews |
| GET | `/reviews/{id}` | Busca por ID |
| GET | `/reviews?book=nome` | Busca por título (parcial, case insensitive) |
| POST | `/reviews` | Cria uma nova review |
| PUT | `/reviews/{id}` | Atualiza completamente |
| PATCH | `/reviews/{id}` | Atualização parcial |
| DELETE | `/reviews/{id}` | Remove uma review |

---

## 🔍 Busca Inteligente

A busca por livro utiliza:

- `Containing` (busca parcial)
- `IgnoreCase` (case insensitive)

Exemplo:
/reviews?book=clean

Retorna:
- Clean Code
- Clean Architecture
- etc

---

## 🛡️ Validações

- Bean Validation nos DTOs
- Regras de negócio no domínio
- Proteção contra campos indevidos no PATCH
- Campos obrigatórios tratados corretamente

---

## ⚠️ Tratamento de Erros

Implementado com `@RestControllerAdvice`

### Tipos tratados:

- 400 → validação / regra de negócio
- 404 → recurso não encontrado
- 409 → conflito (duplicidade)
- 500 → erro interno (fallback)

### Resposta padrão:
{
  "success": false,
  "data": null,
  "message": "Descrição do erro",
  "timestamp": "2026-01-01T00:00:00"
}
---

## 🔁 Padrão de Resposta

Todas as respostas seguem o formato:

{
  "success": true,
  "data": {},
  "message": "Mensagem",
  "timestamp": "..."
}

---

## 💾 Persistência

* PostgreSQL
* JPA/Hibernate
* `GenerationType.IDENTITY` para IDs
* `reviewText` armazenado como `TEXT`

---

## 🧪 Casos tratados

* Validação de entrada inválida
* Proteção contra alteração de campos sensíveis
* Duplicidade de reviews
* Busca parcial
* Tratamento de exceções globais
* Prevenção de erros 500 em cenários previsíveis

---

## 🔥 Diferenciais do Projeto

* Arquitetura limpa e organizada
* Uso de Value Objects no domínio
* Separação clara entre Domain e Infraestrutura
* Uso de Adapter Pattern
* Validações em múltiplas camadas
* Tratamento global de exceções
* Código preparado para evolução (ex: autenticação)

---

## 💡 Modo Alternativo de Persistência (JSON)

Além da integração com PostgreSQL, o projeto também suporta um modo alternativo de persistência utilizando arquivos JSON armazenados localmente.

Esse modo foi pensado para:

- Testes locais rápidos sem necessidade de banco de dados
- Execução simplificada da aplicação
- Uso em interfaces de linha de comando (CLI), que foi a ideia inicial do projeto

### 🔄 Como funciona

A aplicação segue o padrão de Repository com Adapter, permitindo trocar facilmente a implementação de persistência.

Para utilizar arquivos JSON em vez do banco de dados:

- Utilize a implementação `FileReviewRepository`
- Configure a aplicação para usar esse repository no lugar do adapter JPA

### 📌 Vantagens

- Não depende de infraestrutura externa
- Ideal para testes rápidos
- Facilita prototipação e experimentação

Essa abordagem reforça a flexibilidade da arquitetura adotada.

---

## 🚀 Próximos Passos

* 🔐 Autenticação e autorização (Spring Security + JWT)
* 🧪 Testes unitários e de integração
* 📄 Paginação
* 🛠️ Migrations com Flyway
* 📊 Logs e observabilidade

---

## ⚙️ Como rodar o projeto

### 1. Clonar repositório

git clone https://github.com/seu-usuario/terminux-review.git
cd terminux-review

### 2. Configurar banco

Editar `application.properties` com suas credenciais PostgreSQL

### 3. Rodar aplicação

./mvnw spring-boot:run

ou

mvn spring-boot:run

---

## 📌 Autor

Desenvolvido por Vinicius Santos

---

## 📄 Licença

Este projeto é de uso educacional e para portfólio.

