# Sistema de Jogos Digitais

## Equipe
- Pedro Brown
- Thiago Vasconcellos
- Diego Cavalcante

## Sobre o projeto

Sistema web para gerenciamento de jogos digitais, desenvolvido com Spring Boot e Thymeleaf. Permite cadastrar, listar, editar e excluir jogos com campos de nome, plataforma, gênero e preço.

## Tecnologias

- Java 17
- Spring Boot 3.2
- Spring Data JPA
- Thymeleaf
- PostgreSQL 15
- Docker e Docker Compose

## Como executar

**Pré-requisito:** ter Docker e Docker Compose instalados.

```bash
docker compose up --build
```

Acesse em: `http://localhost:8080`

O banco de dados PostgreSQL é iniciado automaticamente pelo Docker Compose junto com a aplicação.

## Funcionalidades

- Listagem de jogos em tabela
- Cadastro de novo jogo
- Edição de jogo existente
- Exclusão de jogo com confirmação
- Mensagem de feedback após salvar ou excluir
