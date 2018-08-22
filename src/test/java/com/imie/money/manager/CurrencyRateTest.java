package com.imie.money.manager;

import org.assertj.core.data.Offset;
import org.junit.Test;

import java.util.Currency;

import static org.assertj.core.api.Assertions.*;

public class CurrencyRateTest {

    @Test
    public void test_getCurrencyRate_nominal_returnExpectedRate() {

        // Init context & mocks
        CurrencyRate currencyRate = new CurrencyRate();
        Currency eur = Currency.getInstance("EUR");
        Currency gbp = Currency.getInstance("GBP");

        // Execute
        Float rate = currencyRate.getCurrencyRate(eur, gbp);

        // Assertions
        assertThat(rate).isCloseTo(0.90f, Offset.offset(0.01f));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getCurrencyRate_nullCurrency_returnIllegalEx() {

        // Init context & mocks
        CurrencyRate currencyRate = new CurrencyRate();
        Currency eur = Currency.getInstance("EUR");

        // Execute
        currencyRate.getCurrencyRate(eur, null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_getCurrencyRate_unknownCurrency_returnUnsupportedEx() {

        // Init context & mocks
        CurrencyRate currencyRate = new CurrencyRate();
        Currency eur = Currency.getInstance("EUR");
        Currency mozambique = Currency.getInstance("MZM");

        // Execute
        currencyRate.getCurrencyRate(eur, mozambique);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_getCurrencyRate_rateReferenceEqualZero_returnUnsupportedEx() {

        // Init context & mocks
        CurrencyRate currencyRate = new CurrencyRate();
        Currency eur = Currency.getInstance("EUR");
        Currency gbp = Currency.getInstance("GBP");
        currencyRate.ratesWithRef.put(eur, 0f);

        // Execute
        currencyRate.getCurrencyRate(eur, gbp);
    }
}