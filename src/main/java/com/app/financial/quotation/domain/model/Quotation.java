package com.app.financial.quotation.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Quotation {
    private Long id;
    private String name;
    private BigDecimal value;
    private LocalDateTime dateLastUpdate = LocalDateTime.now();
}
