# Quotation API

REST API for managing financial quotations, with support for B3 assets and Bitcoin quotes via external integrations. Built with **Spring Boot** following **Clean Architecture**.

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [API](#api)
- [Configuration](#configuration)
- [Running the Project](#running-the-project)
- [External Integrations](#external-integrations)

---

## Features

- **CRUD for quotations** — Create, list, update, and delete quotations.
- **Search by name** — Get a quotation by asset name.
- **Filter by automatic update** — List quotations with automatic update enabled or disabled.
- **Automatic update** — Sync values with B3 (assets) and HgBrasil (Bitcoin).
- **OpenAPI documentation** — Swagger UI for API exploration.
- **Monitoring** — Spring Boot Actuator endpoints.

---

## Tech Stack

| Technology       | Version  | Purpose                |
|------------------|----------|------------------------|
| Java             | 23       | Language               |
| Spring Boot      | 3.4.4    | Framework              |
| Spring Data JPA  | —        | Persistence            |
| PostgreSQL       | —        | Database               |
| Lombok           | 1.18.36  | Boilerplate reduction  |
| SpringDoc OpenAPI| 2.3.0    | Swagger documentation  |
| Apache POI       | 5.2.3    | Excel file reading     |

---

## Architecture

The project follows **Clean Architecture**, with dependencies pointing inward toward the domain:

```
┌─────────────────────────────────────────────────────────────────┐
│  Controllers (REST)                                              │
├─────────────────────────────────────────────────────────────────┤
│  Use Cases (QuotationUseCase, IntegrationQuotationUseCase)       │
├─────────────────────────────────────────────────────────────────┤
│  Domain (Quotation, QuotationRepository)                         │
├─────────────────────────────────────────────────────────────────┤
│  Adapters (B3Gateway, BitcoinGateway)  │  Infrastructure (JPA)   │
└─────────────────────────────────────────────────────────────────┘
```

- **Domain** — `Quotation` model and `QuotationRepository` interface; no framework dependencies.
- **Use cases** — Application rules; orchestrate repository and gateways.
- **Adapters** — Integration with external APIs (B3, HgBrasil).
- **Infrastructure** — Repository implementation with JPA, Entity ↔ Domain mapping, and configuration.

---

## Project Structure

```
src/main/java/com/app/financial/quotation/
├── QuotationApplication.java
├── controller/
│   ├── QuotationController.java           # Quotations CRUD
│   └── IntegrationQuotationController.java  # B3/BTC integrations
├── domain/
│   ├── model/
│   │   └── Quotation.java
│   └── repository/
│       └── QuotationRepository.java      # Persistence port
├── usecase/
│   ├── QuotationUseCase.java              # CRUD use cases
│   └── IntegrationQuotationUseCase.java  # Automatic update
├── adapter/
│   ├── b3/
│   │   ├── B3Gateway.java                 # B3 API client
│   │   └── dto/
│   │       └── B3Dto.java
│   └── btc/
│       ├── BitcoinGateway.java           # HgBrasil client (BTC)
│       ├── dto/                           # HgBrasil response DTOs
│       └── exception/                     # BTC error handling
└── infrastructure/
    ├── config/
    │   ├── PropertiesConfiguration.java  # api.b3, api.hgbrasil
    │   └── RestTemplateConfig.java
    └── persistence/
        ├── entities/
        │   └── QuotationEntity.java       # JPA entity
        ├── QuotationMapper.java           # Domain ↔ Entity
        ├── QuotationEntityRepository.java # Repository implementation
        └── SpringDataQuotationRepository.java # JpaRepository
```

---

## API.

Base URL: `http://localhost:8084/app`

### Quotations

| Method   | Endpoint                    | Description                           |
|----------|-----------------------------|---------------------------------------|
| `GET`    | `/quotation`                | List all quotations                   |
| `GET`    | `/quotation/{id}`           | Get quotation by ID                   |
| `GET`    | `/quotation/name?name=`     | Get quotation by name                 |
| `GET`    | `/quotation/filter?automaticQuotation=` | Filter by automatic update     |
| `POST`   | `/quotation`                | Create new quotation                  |
| `PUT`    | `/quotation/{id}`           | Update quotation                      |
| `DELETE` | `/quotation/{id}`           | Delete quotation                      |

### Integrations

| Method   | Endpoint                         | Description                                  |
|----------|----------------------------------|----------------------------------------------|
| `POST`   | `/quotation/integrations`        | Trigger automatic update (B3 + BTC)          |
| `GET`    | `/quotation/integrations`        | List quotations returned by B3 API           |

### Interactive documentation

- **Swagger UI:** `http://localhost:8084/quotation/swagger-ui.html`
- **OpenAPI JSON:** exposed via SpringDoc

---

## Configuration

### Profiles

- `dev` — Development (default in `application.properties`).
- `prod` — Production.

### Main properties

| Property                  | Description                    | Example (dev)                    |
|---------------------------|--------------------------------|----------------------------------|
| `server.port`             | HTTP port                      | `8084`                           |
| `spring.datasource.url`   | PostgreSQL JDBC URL            | `jdbc:postgresql://localhost:5432/finance` |
| `spring.datasource.username` | Database user               | `usuario`                        |
| `spring.datasource.password` | Database password           | `1234`                           |
| `api.b3`                  | B3 assets API URL              | `http://localhost:8086/app/b3/assets` |
| `api.hgbrasil`            | HgBrasil API URL (finance/BTC) | `http://localhost:8085/hgbrasil/finance` |

External API URLs are read by `PropertiesConfiguration` (prefix `api`).

### Database

- **PostgreSQL** with schema managed by Hibernate (`ddl-auto=update` in dev).
- Table: `quotations` (fields mapped in `QuotationEntity`).

---

## Running the Project

### Prerequisites

- **JDK 23**
- **Gradle** (wrapper included)
- **PostgreSQL** running, with database `finance` created and credentials set in `application-dev.properties` (or environment variables)

### Command

```bash
./gradlew bootRun
```

Or with an explicit profile:

```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

The API will be available at `http://localhost:8084`.

### Build

```bash
./gradlew build
```

### Tests

```bash
./gradlew test
```

---

## External Integrations

### B3 (assets)

- **Property:** `api.b3`
- **Purpose:** List assets and closing price to update stored quotations whose name matches the trading code.
- **Gateway:** `B3Gateway` — GET to configured URL, response mapped to `B3Dto[]`.

### HgBrasil (Bitcoin)

- **Property:** `api.hgbrasil`
- **Purpose:** Bitcoin quote; quotations named `"BTC"` with automatic update enabled are updated with the returned sell value.
- **Gateway:** `BitcoinGateway` — GET to configured URL, response mapped to `QuoteResponseHgBrasil`.

### Automatic update flow

1. Client calls `POST /app/quotation/integrations`.
2. `IntegrationQuotationUseCase` fetches all quotations with automatic update enabled.
3. For each quotation:
   - Name `"BTC"` → update via `BitcoinGateway`.
   - Other names → lookup in B3 list by trading code; on match, update value and keep automatic update enabled.
4. Values are persisted via `QuotationUseCase.updateQuotation`.

---

## License

Internal / proprietary use — per project policy.
