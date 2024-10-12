package ru.skillbox.currency.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRequestDto {

    @NotBlank(message = "Currency name must not be blank.")
    private String name;

    @NotNull(message = "Nominal must not be null.")
    @Positive(message = "Nominal must be a positive number.")
    private Long nominal;

    @NotNull(message = "Value must not be null.")
    @Positive(message = "Value must be a positive number.")
    private Double value;

    @Digits(integer = 3, fraction = 0, message = "ISO numeric code must be exactly 3 digits.")
    @NotNull(message = "ISO numeric code must not be null.")
    @Positive(message = "Value must be a positive number.")
    private Long isoNumCode;

    @Pattern(regexp = "^[A-Z]{3}$", message = "ISO alpha code must be exactly 3 uppercase letters.")
    @NotBlank(message = "ISO alpha code must not be blank.")
    private String isoAlphaCode;
}