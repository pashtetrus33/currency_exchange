package ru.skillbox.currency.exchange.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.model.CurrencyRate;
import ru.skillbox.currency.exchange.model.ValCurs;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyUpdateService {

    private final CurrencyRepository repository;
    private final RestTemplate restTemplate;

    @Value("${cb.api.url}")
    private String apiUrl;


    @Scheduled(cron = "${currency.update.period}") // каждый час
    public void updateCurrencies() {
        try {
            String xmlResponse = restTemplate.getForObject(apiUrl, String.class);

            JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();


            assert xmlResponse != null;
            ValCurs currencyRates = (ValCurs) unmarshaller.unmarshal(new StringReader(xmlResponse));

            int updatedCount = 0;
            int addedCount = 0;


            for (CurrencyRate currencyRate : currencyRates.getValute()) {

                Currency currency = repository.findByIsoNumCode(currencyRate.getNumCode());
                if (currency != null) {
                    // Обновляем существующую запись
                    currency.setValue(currencyRate.getValue());
                    repository.save(currency);
                    updatedCount++;
                } else {

                    Currency newCurrency = new Currency();
                    newCurrency.setIsoAlphaCode(currencyRate.getCharCode());
                    newCurrency.setIsoNumCode(currencyRate.getNumCode());
                    newCurrency.setNominal(currencyRate.getNominal());
                    newCurrency.setName(currencyRate.getName());
                    newCurrency.setValue(currencyRate.getValue());
                    repository.save(newCurrency);
                    addedCount++;
                }
            }
            log.info("Currency rates updated successfully. {} currencies updated, {} currencies added.", updatedCount, addedCount);
        } catch (Exception e) {
            log.error("Error updating currency rates: {}", e.getMessage());
        }
    }
}