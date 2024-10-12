package ru.skillbox.currency.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyResponseDto {
    private Long id;
    private String name;
    private Long nominal;
    private Double value;
    private Long isoNumCode;
    private String isoAlphaCode;
}
