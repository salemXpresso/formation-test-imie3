package com.imie.money.manager;

import com.imie.money.model.Money;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

import java.util.Currency;

public class ConverterTest {

    @Test
    public void test_add_nominal_returnExpectedResult() {

        // Init context & mocks
        Converter converter = new Converter();
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
        Converter converter = new Converter();
        Money m1 = new Money(Currency.getInstance("EUR"), -2.15f);
        Money m2 = new Money(Currency.getInstance("EUR"), 2.85f);

        // Execution
        converter.add(m1, m2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_add_NullMoney_ReturnIllegalEx() {

        // Init context & mocks
        Converter converter = new Converter();
        Money m2 = new Money(Currency.getInstance("EUR"), 2.85f);

        // Execution
        converter.add(null, m2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_add_differentCurrency_returnUnsupportedEx() {

        // Init context & mocks
        Converter converter = new Converter();
        Money m1 = new Money(Currency.getInstance("USD"), 2.15f);
        Money m2 = new Money(Currency.getInstance("EUR"), 2.85f);

        // Execution
        converter.add(m1, m2);
    }
}
