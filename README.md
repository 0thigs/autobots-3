# API Automanager

Um sistema de gerenciamento automotivo desenvolvido com Spring Boot para controle de empresas, usuários, veículos, produtos, serviços e vendas.

## 🚀 Pré-requisitos

Antes de executar a aplicação, certifique-se de ter instalado:

- **Java 17** ou superior
- **Maven 3.6+** (recomendado - o projeto também inclui o Maven Wrapper como alternativa)
- **IDE** (IntelliJ IDEA, Eclipse, VS Code, etc.)

## 🔧 Instruções para Configuração e Execução

### 📋 Opção 1: Executando via Maven (Recomendado)

No terminal, navegue até o diretório `automanager` e execute:

```bash
cd automanager
mvn spring-boot:run
```

### 📋 Opção 2: Executando via Maven Wrapper

**Windows (PowerShell/CMD):**
```bash
cd automanager
.\mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
cd automanager
./mvnw spring-boot:run
```

### 📋 Opção 3: Executando via IDE

1. Abra o projeto na sua IDE preferida
2. Navegue até o arquivo `src/main/java/com/autobots/automanager/AutomanagerApplication.java`
3. Execute o método `main()` diretamente pela IDE

### 📋 Opção 4: Compilando e executando o JAR

```bash
cd automanager
./mvnw clean package
java -jar target/automanager-0.0.1-SNAPSHOT.jar
```

## 🌐 Acessando a Aplicação

Após inicializar a aplicação, ela estará disponível em:
- **URL Base:** `http://localhost:8080`
- **Console H2 Database:** `http://localhost:8080/h2-console`
  - **URL JDBC:** `jdbc:h2:mem:automanagerdb`
  - **Usuário:** `sa`
  - **Senha:** *(vazio)*
- **Documentação Swagger:** `http://localhost:8080/swagger-ui.html`

## 🧪 Testando os Endpoints

Para testar os endpoints desenvolvidos, utilize ferramentas como:
- [Insomnia](https://insomnia.rest/)
- [Postman](https://www.postman.com/)
- [Thunder Client](https://www.thunderclient.com/) (extensão do VS Code)
- Documentação interativa do Swagger (recomendado)

## 📚 Documentação dos Endpoints

### 🏢 Empresa Controller

**Endpoint Base:** `/empresa` e `/empresas`

| Método | Endpoint                  | Descrição                          |
|--------|---------------------------|------------------------------------|
| GET    | `/empresas`               | Lista todas as empresas            |
| GET    | `/empresa/{id}`           | Busca uma empresa pelo ID          |
| POST   | `/empresa/cadastrar`      | Cria uma nova empresa              |
| PUT    | `/empresa/atualizar`      | Atualiza os dados de uma empresa   |
| DELETE | `/empresa/remover`        | Remove uma empresa                 |

---

### 👤 Usuario Controller

**Endpoint Base:** `/usuario`

| Método | Endpoint                    | Descrição                              |
|--------|-----------------------------|----------------------------------------|
| GET    | `/usuario/listar`           | Lista todos os usuários                |
| GET    | `/usuario/{id}`             | Busca um usuário pelo ID               |
| POST   | `/usuario/criar`            | Cria um novo usuário                   |
| PUT    | `/usuario/atualizar/{id}`   | Atualiza os dados de um usuário        |
| DELETE | `/usuario/excluir/{id}`     | Remove um usuário pelo ID              |

---

### 🚗 Veiculo Controller

**Endpoint Base:** `/veiculo`

| Método | Endpoint                     | Descrição                              |
|--------|------------------------------|----------------------------------------|
| GET    | `/veiculo/listar`            | Lista todos os veículos                |
| GET    | `/veiculo/{id}`              | Busca um veículo pelo ID               |
| POST   | `/veiculo/criar`             | Cria um novo veículo                   |
| PUT    | `/veiculo/atualizar/{id}`    | Atualiza os dados de um veículo        |
| DELETE | `/veiculo/excluir/{id}`      | Remove um veículo pelo ID              |

---

### 📦 Mercadoria Controller (Produtos)

**Endpoint Base:** `/mercadoria`

| Método | Endpoint                      | Descrição                             |
|--------|-------------------------------|---------------------------------------|
| GET    | `/mercadoria/listar`          | Lista todos os produtos               |
| GET    | `/mercadoria/{id}`            | Busca um produto pelo ID              |
| POST   | `/mercadoria/cadastrar`       | Cria um novo produto                  |
| PUT    | `/mercadoria/atualizar/{id}`  | Atualiza os dados de um produto       |
| DELETE | `/mercadoria/remover/{id}`    | Remove um produto pelo ID             |

---

### 🛒 Venda Controller

**Endpoint Base:** `/venda`

| Método | Endpoint                  | Descrição                            |
|--------|---------------------------|--------------------------------------|
| GET    | `/venda/listar`           | Lista todas as vendas                |
| GET    | `/venda/{id}`             | Busca uma venda pelo ID              |
| POST   | `/venda/criar`            | Cria uma nova venda                  |
| PUT    | `/venda/atualizar/{id}`   | Atualiza os dados de uma venda       |
| DELETE | `/venda/{id}`             | Remove uma venda pelo ID             |

---

### 🛠️ Servico Controller

**Endpoint Base:** `/servico`

| Método | Endpoint                   | Descrição                              |
|--------|----------------------------|----------------------------------------|
| GET    | `/servico/listar`          | Lista todos os serviços                |
| GET    | `/servico/{id}`            | Busca um serviço pelo ID               |
| POST   | `/servico/criar`           | Cria um novo serviço                   |
| PUT    | `/servico/atualizar/{id}`  | Atualiza os dados de um serviço        |
| DELETE | `/servico/excluir/{id}`    | Remove um serviço pelo ID              |

## 🗄️ Banco de Dados

O projeto utiliza:
- **H2 Database** (em memória) para desenvolvimento e testes
- **MySQL** (configuração disponível no pom.xml)
- **JPA/Hibernate** para mapeamento objeto-relacional

### Configuração do H2
```properties
spring.datasource.url=jdbc:h2:mem:automanagerdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

## 🏗️ Arquitetura do Projeto

```
src/main/java/com/autobots/automanager/
├── controles/          # Controllers REST
├── entidades/          # Entidades JPA
├── enumeracoes/        # Enums do sistema
├── repositorios/       # Repositórios JPA
└── servicos/           # Serviços de negócio
```

## 🔗 Tecnologias Utilizadas

- **Spring Boot 2.6.7**
- **Spring Data JPA**
- **Spring HATEOAS** (para links de navegação)
- **H2 Database** (desenvolvimento)
- **MySQL Connector** (produção)
- **Lombok** (redução de código boilerplate)
- **SpringDoc OpenAPI** (documentação Swagger)
- **Jackson** (serialização JSON)

## 📝 Observações Importantes

1. **Relacionamentos:** Todos os recursos (usuários, veículos, produtos, serviços, vendas) estão vinculados a uma empresa
2. **HATEOAS:** A API implementa HATEOAS para navegação entre recursos
3. **Validações:** Empresa é obrigatória para criação de novos recursos
4. **Banco em Memória:** Dados são perdidos ao reiniciar a aplicação (H2 em memória)

