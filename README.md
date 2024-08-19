# Book Manager API 📚

## Descrição

A **Book Manager API** é uma aplicação RESTful desenvolvida em Java Spring Boot para gerenciar uma coleção de livros. Esta API permite realizar operações de CRUD (Create, Read, Update, Delete) em livros, além de implementar autenticação e autorização utilizando JWT (JSON Web Tokens).

## Funcionalidades

- **Gerenciamento de Livros**: Criação, leitura, atualização e exclusão de livros.
- **Validação de Regras de Negócio**: Regras específicas para garantir a integridade dos dados.
- **Tratamento de Exceções**: Manipulação de erros com respostas HTTP apropriadas.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Maven**

## Requisitos de Sistema

- **JDK 17**
- **Maven 3.6+**
- **Banco de PostgreSQL** 


Acesse a API em: `http://localhost:8080/api/books`

## Endpoints

### Livros

| Método | Endpoint        | Descrição                                  | Autorização |
|--------|-----------------|--------------------------------------------|-------------|
| GET    | /api/books       | Listar todos os livros                    | Leitor/Administrador  |
| GET    | /api/books/{id}  | Obter detalhes de um livro específico     | Leitor/Administrador  |
| POST   | /api/books       | Criar um novo livro                       | Administrador  |
| PUT    | /api/books/{id}  | Atualizar um livro existente              | Administrador  |
| DELETE | /api/books/{id}  | Excluir um livro                          | Administrador  |


## Regras de Negócio

- **Cadastro de Livros Únicos**: Título e autor devem ser únicos.
- **Ano de Publicação**: Deve ser um ano válido (não pode ser no futuro).
- **Consulta Limitada**: O número máximo de livros retornados em uma única requisição é 100, a menos que a paginação seja implementada. 