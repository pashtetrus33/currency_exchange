package ru.skillbox.currency.exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.dto.CurrencyListResponseDto;
import ru.skillbox.currency.exchange.dto.CurrencyRequestDto;
import ru.skillbox.currency.exchange.dto.CurrencyResponseDto;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.service.CurrencyService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/currency")
public class CurrencyController {
    private final CurrencyService service;

    @GetMapping
    public ResponseEntity<CurrencyListResponseDto> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Currency> currencyPage = service.getAllCurrencies(pageable);

        List<CurrencyDto> currencyDtos = currencyPage.getContent().stream()
                .map(currency -> new CurrencyDto(currency.getName(), currency.getValue()))
                .collect(Collectors.toList());

        CurrencyListResponseDto response = new CurrencyListResponseDto(currencyDtos, currencyPage.getTotalPages(),
                currencyPage.getTotalElements());

        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/{id}")
    ResponseEntity<CurrencyResponseDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping(value = "/convert")
    ResponseEntity<BigDecimal> convertValue(@RequestParam("value") Long value, @RequestParam("numCode") Long numCode) {
        return ResponseEntity.ok(service.convertValue(value, numCode));
    }

    @PostMapping("/create")
    ResponseEntity<CurrencyResponseDto> create(@Valid @RequestBody CurrencyRequestDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }
}