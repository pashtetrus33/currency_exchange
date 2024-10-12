package ru.skillbox.currency.exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.currency.exchange.dto.CurrencyRequestDto;
import ru.skillbox.currency.exchange.dto.CurrencyResponseDto;
import ru.skillbox.currency.exchange.service.CurrencyService;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/currency")
public class CurrencyController {
    private final CurrencyService service;

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