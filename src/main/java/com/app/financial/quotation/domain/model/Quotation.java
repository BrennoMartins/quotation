package com.app.financial.quotation.domain.model;

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
public class Quotation {
    private Long id;
    private String name;
    private BigDecimal value;
    private LocalDateTime dateLastUpdate = LocalDateTime.now();
    private boolean automaticUpdateValue;
}
