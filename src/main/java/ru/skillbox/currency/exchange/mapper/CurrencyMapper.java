package ru.skillbox.currency.exchange.mapper;

import org.mapstruct.Mapper;
import ru.skillbox.currency.exchange.dto.CurrencyRequestDto;
import ru.skillbox.currency.exchange.dto.CurrencyResponseDto;
import ru.skillbox.currency.exchange.entity.Currency;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyResponseDto convertToDto(Currency currency);

    Currency convertToEntity(CurrencyRequestDto currencyRequestDto);
}
