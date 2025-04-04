# APP QUOTATION

A aplicação **Quotation** foi feita usando **Clean Architecture**, separando domínio, aplicação, infraestrutura e interface.  
Utiliza **Spring Boot + JPA** com uma camada de entidade (`QuotationEntity`) para persistência, mapeada a partir do modelo de domínio (`Quotation`).  
A API expõe endpoints REST e os dados são manipulados via serviços e repositórios desacoplados.

## Índice

- [Diagrama](#diagrama-architetura-limpa)
- [Infra - Persistence](#infra---persistence)
- [Domain - Repository](#domain---repository)
- [Service](#service)

---

## Diagrama Architetura Limpa

![Diagrama Clean Architecture](./img/diagrama-clean-architecture.png)

---

## Infra - Persistence

Nessa camada inserimos tudo relacionado ao framework que escolhemos para comunicar com o banco de dados.  
Como escolhemos o JPA, vamos primeiro criar o **QuotationMapper**, para transitar do `Model` para `Entity`.

### QuotationMapper

```java
package com.app.financial.quotation.infrastructure.persistence;

import com.app.financial.quotation.domain.model.Quotation;
import com.app.financial.quotation.infrastructure.persistence.entities.QuotationEntity;

public class QuotationMapper {

    public static QuotationEntity toEntity(Quotation quotation) {
        QuotationEntity entityQuotation = new QuotationEntity();
        entityQuotation.setId(quotation.getId());
        entityQuotation.setName(quotation.getName());
        entityQuotation.setValue(quotation.getValue());
        entityQuotation.setDateLastUpdate(quotation.getDateLastUpdate());
        return entityQuotation;
    }

    public static Quotation toDomain(QuotationEntity e) {
        return new Quotation(e.getId(), e.getName(), e.getValue(), e.getDateLastUpdate());
    }
}
```

---

### SpringDataQuotationRepository

Interface para o JPA Spring Cloud

```java
package com.app.financial.quotation.infrastructure.persistence;

import com.app.financial.quotation.infrastructure.persistence.entities.QuotationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataQuotationRepository extends JpaRepository<QuotationEntity, Long> {}
```

---

### JpaQuotationRepository

Classe que implementa o [QuotationRepository](#domain---repository)

```java
package com.app.financial.quotation.infrastructure.persistence;

import com.app.financial.quotation.domain.model.Quotation;
import com.app.financial.quotation.domain.repository.QuotationRepository;
import com.app.financial.quotation.infrastructure.persistence.entities.QuotationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaQuotationRepository implements QuotationRepository {

    private final SpringDataQuotationRepository jpaRepository;

    public JpaQuotationRepository(SpringDataQuotationRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Quotation save(Quotation quotation) {
        QuotationEntity entity = QuotationMapper.toEntity(quotation);
        return QuotationMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Quotation> findById(Long id) {
        return jpaRepository.findById(id).map(QuotationMapper::toDomain);
    }

    @Override
    public List<Quotation> findAll() {
        return jpaRepository.findAll().stream()
                .map(QuotationMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
```

---

## Domain - Repository

Interface para quem quiser implementar os métodos para o Banco de Dados:

```java
package com.app.financial.quotation.domain.repository;

import com.app.financial.quotation.domain.model.Quotation;

import java.util.List;
import java.util.Optional;

public interface QuotationRepository {
    Quotation save(Quotation quotation);
    Optional<Quotation> findById(Long id);
    List<Quotation> findAll();
    void deleteById(Long id);
}
```

---

## Service

`TO DO`

```java
package com.app.financial.quotation.usecase;

import com.app.financial.quotation.domain.model.Quotation;
import com.app.financial.quotation.domain.repository.QuotationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationService {

    private final QuotationRepository repository;

    public QuotationService(QuotationRepository repository) {
        this.repository = repository;
    }

    public Quotation create(Quotation quotation) {
        return repository.save(quotation);
    }

    public List<Quotation> listAll() {
        return repository.findAll();
    }

    public Quotation getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quotation not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
```

---