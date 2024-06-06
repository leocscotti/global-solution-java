# Visionary Blue

Projeto para a entrega de java advanced da global solution.

## Tecnologias

 - [Java 17](https://docs.oracle.com/en/java/javase/17/)
 - [Maven](https://maven.apache.org/)
 - [Spring Boot 3.2.5](https://spring.io/projects/spring-boot)
 - [Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/)
 - [Spring Web Starter](https://docs.spring.io/spring-boot/reference/web/index.html)
 - [Spring Validation Starter](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html)
 - [Spring HATEOAS](https://docs.spring.io/spring-hateoas/docs/current/reference/html/)
 - [Springdoc OpenAPI + Swagger](https://springdoc.org/)
 - [H2 Database](http://h2database.com/html/main.html)
 - [REST](https://ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm)
 - [DTO](https://martinfowler.com/eaaCatalog/dataTransferObject.html)

## Ferramentas

 - [IntelliJ](https://www.jetbrains.com/pt-br/idea/)
 - [Postman](https://www.postman.com/)
## API Reference

#### Obter todos os clientes (findAll clienteDTO)

```http
  GET /clientes
```

#### Obter cliente por id (findById clienteDTO)

```http
  GET /clientes/${id}
```

| Parâmetro | Tipo   | Descrição                                    |
|:----------|:-------|:---------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id do cliente a ser buscado |

#### Gravar cliente (save)

```http
  POST /clientes
```

Body:

```json
{
    "nome": "Exemplo",
    "email": "exemplo@email.com",
    "senha": "123456"
}
```

| Parâmetro | Tipo     | Descrição                       |
|:----------|:---------|:--------------------------------|
| `nome`    | `String` | Nome do cliente a ser gravado   |
| `email`   | `String` | E-mail do cliente a ser gravado |
| `senha`   | `String` | Senha do cliente a ser gravado  |

#### Atualizar cliente (update)

```http
  PUT /clientes/${id}
```

| Parâmetro | Tipo   | Descrição                                       |
|:----------|:-------|:------------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id do cliente a ser atualizado |

Body:

```json
{
    "nome": "Exemplo",
    "email": "exemplo@email.com",
    "senha": "123456"
}
```

| Parâmetro | Tipo     | Descrição                          |
|:----------|:---------|:-----------------------------------|
| `nome`    | `String` | Nome do cliente a ser atualizado   |
| `email`   | `String` | E-mail do cliente a ser atualizado |
| `senha`   | `String` | Senha do cliente a ser atualizado  |

#### Excluir cliente (delete)

```http
  DELETE /clientes/${id}
```

| Parâmetro | Tipo   | Descrição                                     |
|:----------|:-------|:----------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id do cliente a ser excluído |

## Documentação da api-roupas

#### Obter todas as roupas (findAll roupaDTO)

```http
  GET /roupas
```

#### Obter roupa por id (findById roupaDTO)

```http
  GET /roupas/${id}
```

| Parâmetro | Tipo   | Descrição                                    |
|:----------|:-------|:---------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id da roupa a ser buscada |

#### Gravar roupa (save)

```http
  POST /roupa
```

Body:

```json
{
    "tipo": "camiseta",
    "tamanho": "g",
    "cor": "preta",
    "marca": "polo",
    "preco": "40"
}
```

| Parâmetro | Tipo     | Descrição                       |
|:----------|:---------|:--------------------------------|
| `tipo`    | `String` | Tipo da roupa a ser gravada   |
| `tamanho`   | `String` | tamanho da roupa a ser gravada |
| `cor`   | `String` | cor da roupa a ser gravada  |
| `marca`   | `String` | marca da roupa a ser gravada |
| `preco`   | `double` | preço da roupa a ser gravada  |

#### Atualizar roupa (update)

```http
  PUT /roupas/${id}
```

| Parâmetro | Tipo   | Descrição                                       |
|:----------|:-------|:------------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id da roupa a ser atualizada |

Body:

```json
{
   "tipo": "camiseta",
    "tamanho": "g",
    "cor": "preta",
    "marca": "polo",
    "preco": "40"
}
```

| Parâmetro | Tipo     | Descrição                          |
|:----------|:---------|:-----------------------------------|
| `tipo`    | `String` | Tipo da roupa a ser gravada   |
| `tamanho`   | `String` | tamanho da roupa a ser gravada |
| `cor`   | `String` | cor da roupa a ser gravada  |
| `marca`   | `String` | marca da roupa a ser gravada |
| `preco`   | `double` | preço da roupa a ser gravada  |

#### Excluir roupa (delete)

```http
  DELETE /roupas/${id}
```

| Parâmetro | Tipo   | Descrição                                     |
|:----------|:-------|:----------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id da roupa a ser excluída |
