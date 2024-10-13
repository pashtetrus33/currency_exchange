package ru.skillbox.currency.exchange.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.NumberFormat;
import java.util.Locale;

public class DoubleAdapter extends XmlAdapter<String, Double> {

    private final NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));

    @Override
    public Double unmarshal(String value) throws Exception {
        if (value != null) {

            return numberFormat.parse(value).doubleValue();
        }
        return null;
    }

    @Override
    public String marshal(Double value) throws Exception {
        return value != null ? numberFormat.format(value) : null;
    }
}