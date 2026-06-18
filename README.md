# ContraFácil

Sistema back-end para contratação de profissionais autônomos, desenvolvido em Java puro utilizando Maven, princípios SOLID, padrões de projeto e testes unitários.

O objetivo do projeto é permitir que clientes encontrem profissionais autônomos por categoria e avaliação, solicitem contratações e registrem avaliações dos serviços prestados.

Este projeto foi desenvolvido para a disciplina **Modelos, Métodos e Técnicas de Engenharia de Software**, com foco na aplicação prática de análise de requisitos, modelagem, orientação a objetos, SOLID, padrões de projeto, testes e organização de código.

---

## Integrantes

- Leonardo Oliveira Morais — 1242020286
- Luis Filipe da Silva Periard — 1232021702
- Lucas Gabriel Lopes de Souza — 12317548
- Felipe dos Santos Marques — 123115320
- Matheus Tetsuo Paiva Okano — 1221121320

---

## Escopo do Projeto

O ContraFácil foi desenvolvido como um MVP acadêmico, ou seja, uma versão inicial e simplificada da solução.

O foco do projeto é demonstrar a aplicação dos conceitos de Engenharia de Software em uma aplicação back-end Java, sem interface gráfica e sem banco de dados.

### Funcionalidades implementadas

- Cadastro de clientes;
- Cadastro de profissionais autônomos;
- Associação de profissionais a categorias;
- Busca de profissionais por categoria;
- Busca de profissionais por avaliação mínima;
- Combinação de filtros de busca;
- Criação de solicitações de contratação;
- Aceite de solicitações de contratação;
- Recusa de solicitações de contratação;
- Registro de avaliação de profissional;
- Consulta básica do histórico de contratos;
- Execução local via terminal;
- Testes unitários com JUnit.

### Fora do escopo

Não fazem parte desta entrega:

- Interface gráfica;
- Aplicativo mobile;
- Autenticação real de usuários;
- Banco de dados;
- Spring Boot;
- API REST;
- Swagger;
- Pagamento online;
- Chat entre cliente e profissional;
- Geolocalização;
- Integração com serviços externos.

Essas funcionalidades podem ser consideradas evoluções futuras.

---

## Tecnologias Utilizadas

- Java 17
- Maven
- JUnit 5
- Programação Orientada a Objetos
- SOLID
- Design Patterns
- Git e GitHub

---

## Arquitetura do Projeto

O projeto foi organizado em pacotes com responsabilidades separadas, buscando aumentar a coesão, reduzir o acoplamento e facilitar a manutenção.

```text
src
├── main
│   └── java
│       └── com.uni.servicehiring
│           ├── Main.java
│           ├── application
│           │   ├── filter
│           │   └── specification
│           └── domain
│               ├── factory
│               ├── model
│               ├── repository
│               └── service
│
└── test
    └── java
        └── com.uni.servicehiring
```

### Responsabilidade dos pacotes

```text
application.filter
Contém o mecanismo responsável por aplicar filtros aos profissionais.

application.specification
Contém as regras de filtragem reutilizáveis e combináveis.

domain.factory
Contém a fábrica responsável pela criação de usuários.

domain.model
Contém as entidades e enums principais do sistema.

domain.repository
Contém a abstração de repositório e a implementação em memória.

domain.service
Contém os serviços responsáveis pelas regras de negócio principais.

src/test
Contém os testes unitários da aplicação.
```

---

## Principais Classes

```text
Main
Classe principal usada para demonstrar o fluxo da aplicação no terminal.

User
Classe abstrata base para os usuários do sistema.

Client
Representa o cliente que deseja contratar um serviço.

Provider
Representa o profissional autônomo cadastrado na plataforma.

Category
Enum com as categorias de serviços disponíveis.

Contract
Representa uma solicitação de contratação entre cliente e profissional.

ContractStatus
Enum que representa os status de uma solicitação: PENDING, ACCEPTED e REJECTED.

UserFactory
Fábrica responsável pela criação de clientes e profissionais.

ProviderRepository
Interface que define as operações de persistência de profissionais.

InMemoryProviderRepository
Implementação em memória do repositório de profissionais.

ProviderService
Serviço responsável por cadastrar e buscar profissionais.

ContractService
Serviço responsável por criar, aceitar e recusar contratações.

Specification
Interface base para regras de filtro.

CategorySpecification
Filtro por categoria de profissional.

RatingSpecification
Filtro por avaliação mínima.

CompositeSpecification
Combina múltiplos filtros.

ProviderFilterEngine
Aplica uma Specification sobre uma lista de profissionais.
```

---

## Design Patterns Utilizados

### Factory Pattern

Utilizado na classe:

```text
UserFactory
```

A `UserFactory` centraliza a criação de objetos do tipo `Client` e `Provider`.

Benefícios:

- Centraliza a criação de usuários;
- Evita espalhar chamadas de construtores pelo sistema;
- Facilita futuras regras de criação;
- Reduz acoplamento.

---

### Repository Pattern

Utilizado em:

```text
ProviderRepository
InMemoryProviderRepository
```

O padrão Repository separa as regras de negócio da forma de armazenamento dos dados.

Na versão atual, a persistência é feita em memória. Futuramente, a implementação poderia ser substituída por banco de dados sem alterar a lógica principal dos serviços.

Benefícios:

- Desacopla regra de negócio da persistência;
- Facilita testes;
- Permite troca futura da implementação.

---

### Specification Pattern

Utilizado em:

```text
Specification
CategorySpecification
RatingSpecification
CompositeSpecification
ProviderFilterEngine
```

O padrão Specification permite criar regras de filtro reutilizáveis e combináveis.

Exemplo de uso:

```java
var specification = new CompositeSpecification(
        new CategorySpecification(Category.CARPENTRY),
        new RatingSpecification(4.5)
);
```

Esse filtro busca profissionais da categoria `CARPENTRY` com avaliação mínima de `4.5`.

Benefícios:

- Facilita criação de novos filtros;
- Permite combinar regras;
- Evita condicionais complexas;
- Favorece o Open/Closed Principle.

---

## Aplicação dos Princípios SOLID

### S — Single Responsibility Principle

Cada classe possui uma responsabilidade principal.

Exemplos:

```text
ProviderService
Responsável por cadastro e busca de profissionais.

ContractService
Responsável por criação, aceite e recusa de contratos.

ProviderFilterEngine
Responsável por aplicar filtros.

UserFactory
Responsável por criar usuários.
```

---

### O — Open/Closed Principle

O sistema permite adicionar novos filtros sem modificar o motor de busca.

Exemplo:

```text
Para criar um novo filtro, basta implementar a interface Specification.
```

---

### L — Liskov Substitution Principle

Classes que implementam a interface `Specification` podem ser utilizadas de forma intercambiável pelo `ProviderFilterEngine`.

Exemplos:

```text
CategorySpecification
RatingSpecification
CompositeSpecification
```

Todas podem ser usadas onde uma `Specification` é esperada.

---

### I — Interface Segregation Principle

As interfaces do projeto são pequenas e específicas.

Exemplos:

```text
Specification
ProviderRepository
```

Essas interfaces possuem apenas os métodos necessários para seus contextos.

---

### D — Dependency Inversion Principle

Os serviços dependem de abstrações, não de implementações concretas.

Exemplo:

```java
private final ProviderRepository repository;
```

O `ProviderService` depende da interface `ProviderRepository`, e não diretamente de `InMemoryProviderRepository`.

---

## Como Executar o Projeto

### Pré-requisitos

Antes de executar o projeto, é necessário ter instalado:

- Java 17 ou superior;
- Maven;
- Git.

Para verificar:

```bash
java -version
```

```bash
mvn -version
```

```bash
git --version
```

---

### Clonar o Repositório

```bash
git clone <URL_DO_REPOSITORIO>
```

Entrar na pasta do projeto:

```bash
cd service-hiring-system
```

---

### Compilar o Projeto

```bash
mvn clean compile
```

---

### Executar a Aplicação

```bash
mvn exec:java
```

A aplicação será executada no terminal por meio da classe `Main`.

A saída esperada é semelhante a:

```text
======================================
CONTRAFÁCIL - DEMONSTRAÇÃO BACK-END
======================================

1. Profissionais cadastrados:
Profissional: Leo | Categorias: [CARPENTRY] | Avaliação: 4.8 | Serviços concluídos: 120
Profissional: Lucas | Categorias: [CLEANING] | Avaliação: 4.2 | Serviços concluídos: 80
Profissional: Felipe | Categorias: [ELECTRICAL, CARPENTRY] | Avaliação: 4.3 | Serviços concluídos: 35

2. Filtro aplicado:
Categoria: CARPENTRY
Avaliação mínima: 4.5

3. Resultado da busca:
Profissional: Leo | Categorias: [CARPENTRY] | Avaliação: 4.8 | Serviços concluídos: 120

4. Criação de cliente:
Cliente criado: Luis | E-mail: luis@gmail.com

5. Solicitação de contratação:
Contrato criado: <id-gerado>
Cliente: Luis
Profissional: Leo
Descrição: Serviço de carpintaria residencial.
Status inicial: PENDING

6. Aceite da solicitação:
Status após aceite: ACCEPTED
Serviços concluídos do profissional: 121

7. Registro de avaliação:
Avaliação antes: 4.8
Nova avaliação registrada: 5.0
Avaliação atualizada: 4.9

8. Histórico básico de contratos:
Contrato: <id-gerado> | Cliente: Luis | Profissional: Leo | Status: ACCEPTED

Demonstração finalizada com sucesso.
```

---

### Executar os Testes

```bash
mvn clean test
```

O resultado esperado é:

```text
BUILD SUCCESS
```

---

## Testes Unitários

Os testes unitários foram criados com JUnit 5 para validar os principais comportamentos da aplicação.

### Testes previstos/implementados

```text
ProviderServiceTest
Valida cadastro, busca e filtro de profissionais.

UserFactoryTest
Valida criação de clientes e profissionais.

ContractServiceTest
Valida criação, aceite e recusa de contratos.

CategorySpecificationTest
Valida filtro por categoria.

RatingSpecificationTest
Valida filtro por avaliação mínima.

CompositeSpecificationTest
Valida combinação de filtros.

ProviderFilterEngineTest
Valida aplicação de Specification sobre uma lista de profissionais.
```

### Cenários positivos

- Criar cliente com dados válidos;
- Criar profissional com dados válidos;
- Cadastrar profissional;
- Buscar profissional por categoria;
- Buscar profissional por avaliação mínima;
- Combinar filtros de categoria e avaliação;
- Criar contrato com status inicial `PENDING`;
- Aceitar contrato;
- Recusar contrato;
- Registrar avaliação válida.

### Cenários negativos

- Criar cliente sem senha;
- Criar profissional sem categoria;
- Criar profissional com avaliação inválida;
- Criar contrato sem cliente;
- Criar contrato sem profissional;
- Criar contrato sem descrição;
- Aceitar contrato já finalizado;
- Registrar avaliação menor que 1;
- Registrar avaliação maior que 5.

---

## Modelagem

Os diagramas do projeto estão disponíveis na pasta:

```text
diagramas/
```

Diagramas esperados:

```text
diagrama-classes.drawio
diagrama-classes.png
diagrama-casos-uso.drawio
diagrama-casos-uso.png
```

O diagrama de classes representa a estrutura principal do sistema, incluindo entidades, serviços, repositórios, fábrica, filtros e relacionamentos.

O diagrama de casos de uso representa as interações entre os atores e as principais funcionalidades do sistema.

---

## Documentação

A documentação do trabalho está disponível na pasta:

```text
documentacao/
```

Arquivo esperado:

```text
Trabalho_A3_ContraFacil.pdf
```

A documentação contempla:

- definição do problema;
- solução proposta;
- escopo do MVP;
- usuários e atores;
- levantamento de requisitos;
- regras de negócio;
- arquitetura;
- padrões de projeto;
- aplicação dos princípios SOLID;
- modelagem;
- testes;
- rastreabilidade;
- conclusão.

---

## Estrutura Final Esperada do Repositório

```text
service-hiring-system
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── uni
│   │               └── servicehiring
│   │                   ├── Main.java
│   │                   ├── application
│   │                   │   ├── filter
│   │                   │   └── specification
│   │                   └── domain
│   │                       ├── factory
│   │                       ├── model
│   │                       ├── repository
│   │                       └── service
│   └── test
│       └── java
│           └── com
│               └── uni
│                   └── servicehiring
├── diagramas
│   ├── diagrama-classes.drawio
│   ├── diagrama-classes.png
│   ├── diagrama-casos-uso.drawio
│   └── diagrama-casos-uso.png
├── documentacao
│   └── Trabalho_A3_ContraFacil.pdf
├── .gitignore
├── pom.xml
└── README.md
```

---

## Observações

O projeto não possui interface gráfica, pois o escopo foi delimitado como uma aplicação back-end em Java puro.

A execução da aplicação ocorre pelo terminal, por meio da classe `Main`, que demonstra o fluxo principal do sistema:

```text
cadastro de profissionais;
aplicação de filtros;
criação de cliente;
solicitação de contratação;
aceite de contrato;
registro de avaliação;
consulta básica de histórico.
```

A validação técnica do comportamento da aplicação é feita por meio dos testes unitários com JUnit.
