package com.imie.money.manager;

import com.imie.money.model.Money;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Currency;

public class ConverterTest {

    private CurrencyRate currencyRate;
    private Converter converter;

    @Before
    public void setUp() {
        currencyRate = mock(CurrencyRate.class);
        converter = new Converter(currencyRate);
    }

    @Test
    public void test_add_nominal_returnExpectedResult() {

        // Init context & mocks
        Money m1 = new Money(Currency.getInstance("EUR"), 2.15f);
        Money m2 = new Money(Currency.getInstance("EUR"), 2.85f);

        // Execution
        Money res = converter.add(m1, m2);

        // Assertions
        assertThat(res).isEqualToComparingFieldByField(new Money(Currency.getInstance("EUR"), 5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_add_sameCurrencyNegativeValue_returnIllegalEx() {

        // Init context & mocks
        Money m1 = new Money(Currency.getInstance("EUR"), -2.15f);
        Money m2 = new Money(Currency.getInstance("EUR"), 2.85f);

        // Execution
        converter.add(m1, m2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_add_NullMoney_ReturnIllegalEx() {

        // Init context & mocks
        Money m2 = new Money(Currency.getInstance("EUR"), 2.85f);

        // Execution
        converter.add(null, m2);
    }

    @Test
    public void test_add_differentCurrency_returnCurrency1AndRatedResult() {

        // Init context & mocks
        Currency c1 = Currency.getInstance("USD");
        Currency c2 = Currency.getInstance("EUR");
        Money m1 = new Money(c1, 2f);
        Money m2 = new Money(c2, 2f);
        when(currencyRate.getCurrencyRate(c1, c2)).thenReturn(1.2f);

        // Execution
        Money m = converter.add(m1, m2);

        // Assertions
        assertThat(m).isEqualToComparingFieldByField(new Money(c1, 4.4f));
    }
}
