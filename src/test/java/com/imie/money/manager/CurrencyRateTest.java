package com.imie.money.manager;

import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Currency;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyRateTest {

    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency EUR = Currency.getInstance("EUR");
    private static final Currency GBP = Currency.getInstance("GBP");

    @InjectMocks
    private CurrencyRate currencyRate;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        currencyRate.ratesWithRef = new HashMap<>();
        currencyRate.ratesWithRef.put(USD, 1f);
        currencyRate.ratesWithRef.put(EUR, 1.16f);
        currencyRate.ratesWithRef.put(GBP, 1.29f);
    }

    @Test
    public void test_getCurrencyRate_nominal_returnExpectedRate() {

        // Init context & mocks
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
        Currency eur = Currency.getInstance("EUR");

        // Execute
        currencyRate.getCurrencyRate(eur, null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_getCurrencyRate_unknownCurrency_returnUnsupportedEx() {

        // Init context & mocks
        Currency eur = Currency.getInstance("EUR");
        Currency mozambique = Currency.getInstance("MZM");

        // Execute
        currencyRate.getCurrencyRate(eur, mozambique);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_getCurrencyRate_rateReferenceEqualZero_returnUnsupportedEx() {

        // Init context & mocks
        Currency eur = Currency.getInstance("EUR");
        Currency gbp = Currency.getInstance("GBP");
        currencyRate.ratesWithRef.put(eur, 0f);

        // Execute
        currencyRate.getCurrencyRate(eur, gbp);
    }
}