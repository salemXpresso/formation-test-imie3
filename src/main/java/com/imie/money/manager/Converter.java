package com.imie.money.manager;

import com.imie.money.model.Money;

public class Converter {

    private CurrencyRate currencyRate;

    public Converter(CurrencyRate currencyRate) {

        this.currencyRate = currencyRate;
    }

    public Money add(Money m1, Money m2) {

        if(m1==null || m2==null) {
            throw new IllegalArgumentException("Money should not be null");
        }

        if(m1.getAmount() < 0 || m2.getAmount() < 0) {
            throw new IllegalArgumentException("Amounts must be positive figures");
        }

        if(!m1.getCurrency().equals(m2.getCurrency())) {
            return new Money(m1.getCurrency(), m1.getAmount() +
                    m2.getAmount() * currencyRate.getCurrencyRate(m1.getCurrency(), m2.getCurrency()));
        }

        return new Money(m1.getCurrency(), m1.getAmount() + m2.getAmount());
    }
}