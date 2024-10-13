package ru.skillbox.currency.exchange.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Valute")
public class CurrencyRate {

    @XmlElement(name = "NumCode")
    private Long numCode;

    @XmlElement(name = "CharCode")
    private String charCode;

    @XmlElement(name = "Nominal")
    private Long nominal;

    @XmlElement(name = "Name")

    private String name;

    @XmlElement(name = "Value")
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    private Double value;
}