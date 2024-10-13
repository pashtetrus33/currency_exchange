package ru.skillbox.currency.exchange.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Setter
@Getter
@XmlRootElement(name = "ValCurs")
public class ValCurs {

    private List<CurrencyRate> valute;

    @XmlElement(name = "Valute")
    public List<CurrencyRate> getValute() {
        return valute;
    }
}