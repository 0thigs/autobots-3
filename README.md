# API Automanager

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
- **Console H2 Database:** `http://localhost:8080/h2-console` (se habilitado)

## 🧪 Testando os Endpoints

Para testar os endpoints desenvolvidos, utilize ferramentas como:
- [Insomnia](https://insomnia.rest/)
- [Postman](https://www.postman.com/)
- [Thunder Client](https://www.thunderclient.com/) (extensão do VS Code)

### Exemplos de teste:

Obs: Todos os JSONS estão na pasta <u>examples</u> dentro do projeto.

## 🏢 CompanyController

**Endpoint Base:** `/companies`

| Método | Endpoint            | Descrição                          |
|--------|---------------------|------------------------------------|
| GET    | `/companies`        | Lista todas as empresas            |
| GET    | `/companies/{id}`   | Busca uma empresa pelo ID          |
| POST   | `/companies`        | Cria uma nova empresa              |
| PUT    | `/companies/{id}`   | Atualiza os dados de uma empresa   |
| DELETE | `/companies/{id}`   | Remove uma empresa pelo ID         |

---

## 👤 UserController

**Endpoint Base:** `/users`

| Método | Endpoint         | Descrição                              |
|--------|------------------|----------------------------------------|
| GET    | `/users`         | Lista todos os usuários                |
| GET    | `/users/{id}`    | Busca um usuário pelo ID               |
| POST   | `/users`         | Cria um novo usuário                   |
| PUT    | `/users/{id}`    | Atualiza os dados de um usuário        |
| DELETE | `/users/{id}`    | Remove um usuário pelo ID              |



---

## 🚗 VehicleController

**Endpoint Base:** `/vehicles`

| Método | Endpoint         | Descrição                              |
|--------|------------------|----------------------------------------|
| GET    | `/vehicles`      | Lista todos os veículos                |
| GET    | `/vehicles/{id}` | Busca um veículo pelo ID               |
| POST   | `/vehicles`      | Cria um novo veículo                   |
| PUT    | `/vehicles/{id}` | Atualiza os dados de um veículo        |
| DELETE | `/vehicles/{id}` | Remove um veículo pelo ID              |

---

## 📦 MerchandiseController

**Endpoint Base:** `/merchandises`

| Método | Endpoint              | Descrição                             |
|--------|-----------------------|---------------------------------------|
| GET    | `/merchandises`       | Lista todos os itens de mercadoria    |
| GET    | `/merchandises/{id}`  | Busca um item de mercadoria pelo ID   |
| POST   | `/merchandises`       | Cria um novo item de mercadoria       |
| PUT    | `/merchandises/{id}`  | Atualiza os dados de um item          |
| DELETE | `/merchandises/{id}`  | Remove um item pelo ID                |

---

## 🛒 SaleController

**Endpoint Base:** `/sales`

| Método | Endpoint      | Descrição                            |
|--------|---------------|--------------------------------------|
| GET    | `/sales`      | Lista todas as vendas                |
| GET    | `/sales/{id}` | Busca uma venda pelo ID              |
| POST   | `/sales`      | Cria uma nova venda                  |
| PUT    | `/sales/{id}` | Atualiza os dados de uma venda       |
| DELETE | `/sales/{id}` | Remove uma venda pelo ID             |

---

## 🛠️ ServiceController

**Endpoint Base:** `/services`

| Método | Endpoint         | Descrição                              |
|--------|------------------|----------------------------------------|
| GET    | `/services`      | Lista todos os serviços                |
| GET    | `/services/{id}` | Busca um serviço pelo ID               |
| POST   | `/services`      | Cria um novo serviço                   |
| PUT    | `/services/{id}` | Atualiza os dados de um serviço        |
| DELETE | `/services/{id}` | Remove um serviço pelo ID              |

