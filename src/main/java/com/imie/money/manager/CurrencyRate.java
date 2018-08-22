package com.imie.money.manager;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class CurrencyRate {

    private Map<Currency, Float> ratesWithRef = new HashMap<>();
    private Currency USD = Currency.getInstance("USD");
    private Currency EUR = Currency.getInstance("EUR");
    private Currency GBP = Currency.getInstance("GBP");


    public CurrencyRate() {
        // Currency reference = USD
        ratesWithRef.put(USD, 1f);
        ratesWithRef.put(EUR, 1.16f);
        ratesWithRef.put(GBP, 1.29f);
    }

    public float getCurrencyRate(Currency c1, Currency c2) {

        if(c1 == null || c2 == null) {
            throw new IllegalArgumentException("Currencies should not be null");
        }

        Float rateWithRef1 = ratesWithRef.get(c1);
        Float rateWithRef2 = ratesWithRef.get(c2);

        if(rateWithRef1 == null || rateWithRef2 == null) {
            throw new UnsupportedOperationException
                    ("At least one of the input currencies is unknown by the application");
        }

        return rateWithRef1 / rateWithRef2;
    }
}
