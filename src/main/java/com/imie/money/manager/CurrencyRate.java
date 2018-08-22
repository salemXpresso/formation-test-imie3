package com.imie.money.manager;

import com.imie.money.connector.CurrencyRateWsClient;

import java.io.IOException;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class CurrencyRate {

    protected Map<Currency, Float> ratesWithRef = new HashMap<>();

    public CurrencyRate(CurrencyRateWsClient currencyRateWsClient, String apiKey) throws IOException {

        Currency reference = Currency.getInstance("USD");
        ratesWithRef = currencyRateWsClient.downloadLatestCurrencyRates(apiKey, reference);
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

        if(rateWithRef1 == 0 || rateWithRef2 == 0) {
            throw new UnsupportedOperationException
                    ("One of the input currencies rate is zero. Can't compute the exchange rate");
        }

        return rateWithRef1 / rateWithRef2;
    }
}
