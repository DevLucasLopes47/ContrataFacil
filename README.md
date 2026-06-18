# ContrataFacil

Sistema backend para contratação de prestadores de serviços autônomos, desenvolvido em Java puro utilizando Maven, princípios SOLID e Design Patterns.

O objetivo do projeto é permitir que clientes encontrem e contratem profissionais de diversas áreas, como carpinteiros, vidraceiros, diaristas, eletricistas e encanadores.

---

# Grupo

- Leonardo Oliveira Morais; 1242020286
- Luis Filipe da Silva Periard; 1232021702
- Lucas Gabriel Lopes de Souza; 12317548
- Felipe dos Santos Marques; 123115320
- Matheus Tetsuo Paiva Okano; 1221121320 

# Funcionalidades

## Usuários

Existem dois tipos de usuários:

### Cliente

Representa quem deseja contratar serviços.

Possui:

- ID
- Nome
- E-mail

### Prestador de Serviço

Representa profissionais autônomos cadastrados na plataforma.

Possui:

- ID
- Nome
- E-mail
- Categorias de atuação
- Avaliação
- Quantidade de serviços realizados

---

## Cadastro de Prestadores

Permite registrar prestadores contendo:

- Uma ou mais categorias
- Avaliação inicial
- Histórico de serviços

Exemplo:

```java
Provider provider = UserFactory.createProvider(
    "1",
    "João",
    "joao@email.com",
    Set.of(Category.CARPENTRY),
    4.8,
    120
);
```

---

## Busca e Filtros

O sistema permite buscar prestadores utilizando filtros combináveis.

Filtros disponíveis:

- Categoria
- Avaliação mínima

Exemplo:

```java
var spec = new CompositeSpecification(
    new CategorySpecification(Category.CARPENTRY),
    new RatingSpecification(4.5)
);
```

---

## Contratação

Permite que um cliente realize uma contratação simples de um prestador.

Exemplo:

```java
Contract contract =
        contractService.createContract(client, provider);
```

---

# Arquitetura

O projeto segue uma arquitetura em camadas:

```text
Application
│
├── Specifications
├── Filter Engine
│
Domain
│
├── Models
├── Services
├── Factories
├── Repositories
│
Infrastructure
│
└── In-Memory Repository
```

---

# Estrutura do Projeto

```text
service-hiring-system
│
├── pom.xml
├── README.md
│
├── src
│   ├── main
│   │   └── java
│   │       └── com.uni.servicehiring
│   │
│   │           ├── Main.java
│   │           │
│   │           ├── application
│   │           │   ├── filter
│   │           │   └── specification
│   │           │
│   │           ├── domain
│   │           │   ├── model
│   │           │   ├── factory
│   │           │   ├── repository
│   │           │   └── service
│   │
│   └── test
│       └── java
│           └── com.uni.servicehiring
│
└── README.md
```

---

# Design Patterns Utilizados

## Factory Pattern

Responsável pela criação de usuários.

Classe:

```java
UserFactory
```

Benefícios:

- Centraliza criação de objetos
- Facilita futuras regras de negócio
- Evita acoplamento com construtores

---

## Specification Pattern

Responsável pelos filtros de busca.

Classes:

```java
Specification
CategorySpecification
RatingSpecification
CompositeSpecification
```

Benefícios:

- Fácil adição de novos filtros
- Não exige alteração de código existente
- Segue Open/Closed Principle

---

## Repository Pattern

Abstrai a camada de persistência.

Interface:

```java
ProviderRepository
```

Implementação atual:

```java
InMemoryProviderRepository
```

Benefícios:

- Facilita troca para banco de dados
- Reduz acoplamento

---

# Aplicação dos Princípios SOLID

## S — Single Responsibility Principle

Cada classe possui uma única responsabilidade.

Exemplos:

- ProviderService → gerencia prestadores
- ContractService → gerencia contratações
- ProviderFilterEngine → executa filtros

---

## O — Open/Closed Principle

Novos filtros podem ser adicionados sem modificar código existente.

Exemplo:

```java
ExperienceSpecification
LocationSpecification
TagSpecification
```

---

## L — Liskov Substitution Principle

Cliente e Prestador podem ser utilizados através da abstração:

```java
User
```

---

## I — Interface Segregation Principle

Interfaces pequenas e específicas.

Exemplo:

```java
ProviderRepository
```

---

## D — Dependency Inversion Principle

Serviços dependem de abstrações.

Exemplo:

```java
ProviderRepository
```

e não da implementação concreta.

---

# Executando o Projeto

## Requisitos

- Java 17+
- Maven 3.9+

---

## Clonar o Projeto

```bash
git clone https://github.com/seu-usuario/service-hiring-system.git
```

---

## Entrar no Projeto

```bash
cd service-hiring-system
```

---

## Compilar

```bash
mvn clean compile
```

---

## Executar

```bash
mvn exec:java
```

ou

```bash
java -cp target/classes com.uni.servicehiring.Main
```

---

# Executando os Testes

```bash
mvn test
```

---

# Exemplo de Uso

## Cadastro

```java
providerService.registerProvider(provider);
```

## Busca

```java
providerService.search(specification);
```

## Contratação

```java
contractService.createContract(client, provider);
```

---

# Tecnologias Utilizadas

- Java 17
- Maven
- JUnit 5
- Programação Orientada a Objetos
- SOLID
- Design Patterns
