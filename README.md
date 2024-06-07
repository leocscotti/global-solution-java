# Visionary Blue

Projeto para a entrega de java advanced da global solution.

## Tecnologias

 - [Java 17](https://docs.oracle.com/en/java/javase/17/)
 - [Maven](https://maven.apache.org/)
 - [Spring Boot 3.2.5](https://spring.io/projects/spring-boot)
 - [Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/)
 - [Spring Web Starter](https://docs.spring.io/spring-boot/reference/web/index.html)
 - [Spring Validation Starter](https://docs.spring.io/spring-framework/reference/situacaoe/validation/beanvalidation.html)
 - [Spring HATEOAS](https://docs.spring.io/spring-hateoas/docs/current/reference/html/)
 - [Springdoc OpenAPI + Swagger](https://springdoc.org/)
 - [H2 Database](http://h2database.com/html/main.html)
 - [REST](https://ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm)
 - [DTO](https://martinfowler.com/eaaCatalog/dataTransferObject.html)

## Ferramentas

 - [IntelliJ](https://www.jetbrains.com/pt-br/idea/)
 - [Postman](https://www.postman.com/)
## API Reference

#### Obter todos os usuarios (findAll usuarioDTO)

```http
  GET /usuarios
```

#### Obter usuario por id (findById usuarioDTO)

```http
  GET /usuarios/${id}
```

| Parâmetro | Tipo   | Descrição                                    |
|:----------|:-------|:---------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id do usuario a ser buscado |

#### Gravar usuario (save)

```http
  POST /usuarios
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
| `nome`    | `String` | Nome do usuario a ser gravado   |
| `email`   | `String` | E-mail do usuario a ser gravado |
| `senha`   | `String` | Senha do usuario a ser gravado  |

#### Atualizar usuario (update)

```http
  PUT /usuarios/${id}
```

| Parâmetro | Tipo   | Descrição                                       |
|:----------|:-------|:------------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id do usuario a ser atualizado |

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
| `nome`    | `String` | Nome do usuario a ser atualizado   |
| `email`   | `String` | E-mail do usuario a ser atualizado |
| `senha`   | `String` | Senha do usuario a ser atualizado  |

#### Excluir usuario (delete)

```http
  DELETE /usuarios/${id}
```

| Parâmetro | Tipo   | Descrição                                     |
|:----------|:-------|:----------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id do usuario a ser excluído |

## Documentação da api-denuncia

#### Obter todas as denuncias (findAll denunciaDTO)

```http
  GET /denuncias
```

#### Obter denuncia por id (findById denunciaDTO)

```http
  GET /denuncias/${id}
```

| Parâmetro | Tipo   | Descrição                                    |
|:----------|:-------|:---------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id da denuncia a ser buscada |

#### Gravar denuncia (save)

```http
  POST /denuncias
```

Body:

```json
{
    "descricao": "descarte de lixo",
    "situacao": "ativa",
    "localizacao": "praia do rosa"
}
```

| Parâmetro | Tipo     | Descrição                       |
|:----------|:---------|:--------------------------------|
| `descricao`    | `String` | Tipo da denuncia a ser gravada   |
| `situacao`   | `String` | situacao da denuncia a ser gravada  |
| `localizacao`   | `String` | localizacao da denuncia a ser gravada |

#### Atualizar denuncia (update)

```http
  PUT /denuncias/${id}
```

| Parâmetro | Tipo   | Descrição                                       |
|:----------|:-------|:------------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id da denuncia a ser atualizada |

Body:

```json
{
   "descricao": "descarte de lixo",
    "situacao": "ativa",
    "localizacao": "praia do rosa"
}
```

| Parâmetro | Tipo     | Descrição                          |
|:----------|:---------|:-----------------------------------|
| `descricao`    | `String` | Tipo da denuncia a ser gravada   |
| `situacao`   | `String` | situacao da denuncia a ser gravada  |
| `localizacao`   | `String` | localizacao da denuncia a ser gravada |


#### Excluir denuncia (delete)

```http
  DELETE /denuncias/${id}
```

| Parâmetro | Tipo   | Descrição                                     |
|:----------|:-------|:----------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id da denuncia a ser excluída |

## Documentação da api-residuo

#### Obter todos os residuos (findAll residuoDTO)

```http
  GET /residuos
```

#### Obter residuo por id (findById residuoDTO)

```http
  GET /residuos/${id}
```

| Parâmetro | Tipo   | Descrição                                    |
|:----------|:-------|:---------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id do residuo a ser buscado |

#### Gravar residuo (save)

```http
  POST /residuos
```

Body:

```json
{
    "descricao": "descarte de liquidos",
    "tipo": "quimico",
    "quantidade": 123
}
```

| Parâmetro | Tipo     | Descrição                       |
|:----------|:---------|:--------------------------------|
| `descricao`    | `String` | Descricao do residuo a ser gravado   |
| `tipo`   | `String` | Tipo do residuo a ser gravado |
| `quantidade`   | `int` | Quantidade do residuo a ser gravado  |

#### Atualizar residuo (update)

```http
  PUT /residuos/${id}
```

| Parâmetro | Tipo   | Descrição                                       |
|:----------|:-------|:------------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id do residuo a ser atualizado |

Body:

```json
{
    "descricao": "descarte de liquidos",
    "tipo": "quimico",
    "quantidade": 123
}
```

| Parâmetro | Tipo     | Descrição                          |
|:----------|:---------|:-----------------------------------|
| `descricao`    | `String` | Descricao do residuo a ser gravado   |
| `tipo`   | `String` | Tipo do residuo a ser gravado |
| `quantidade`   | `int` | Quantidade do residuo a ser gravado  |


#### Excluir residuo (delete)

```http
  DELETE /residuos/${id}
```

| Parâmetro | Tipo   | Descrição                                     |
|:----------|:-------|:----------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id do residuo a ser excluído |


## Documentação da api-autoridadesambientais

#### Obter todas as autoridades ambientais (findAll autoridadeambientalDTO)

```http
  GET /autoridadesambientais
```

#### Obter autoridade ambiental por id (findById autoridadeambientalDTO)

```http
  GET /autoridadesambientais/${id}
```

| Parâmetro | Tipo   | Descrição                                    |
|:----------|:-------|:---------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id da autoridade ambiental a ser buscada |

#### Gravar autoridade ambientai (save)

```http
  POST /autoridadesambientais
```

Body:

```json
{
    "nome": "leonardo",
    "tipo": "agro",
    "cargo": "coordenador"
}
```

| Parâmetro | Tipo     | Descrição                       |
|:----------|:---------|:--------------------------------|
| `nome`    | `String` | Nome da autoridade ambiental a ser gravada   |
| `tipo`   | `String` | tipo da autoridade ambiental a ser gravada |
| `cargo`   | `String` | cargo da autoridade ambiental a ser gravada  |

#### Atualizar autoridade ambiental (update)

```http
  PUT /autoridadesambientais/${id}
```

| Parâmetro | Tipo   | Descrição                                       |
|:----------|:-------|:------------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id da autoridade ambiental a ser atualizada |

Body:

```json
{
    "nome": "leonardo",
    "tipo": "agro",
    "cargo": "coordenador"
}
```

| Parâmetro | Tipo     | Descrição                       |
|:----------|:---------|:--------------------------------|
| `nome`    | `String` | Nome da autoridade ambiental a ser gravada   |
| `tipo`   | `String` | tipo da autoridade ambiental a ser gravada |
| `cargo`   | `String` | cargo da autoridade ambiental a ser gravada  |


#### Excluir autoridade ambiental (delete)

```http
  DELETE /autoridadesambientais/${id}
```

| Parâmetro | Tipo   | Descrição                                     |
|:----------|:-------|:----------------------------------------------|
| `id`      | `Long` | **Obrigatório**. Id da autoridade ambiental a ser excluída |
