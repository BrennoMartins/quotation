package com.app.financial.quotation.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "quotations")
public class QuotationEntity {
    @Id
    @Column(name = "id_quotation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_quotation")
    private String name;

    @Column(name = "value_quotation")
    private BigDecimal value;

    @Column(name = "datetime_last_update_quotation")
    private LocalDateTime dateLastUpdate;

    @Column(name = "automatic_update_value")
    private boolean automaticUpdateValue;
}
