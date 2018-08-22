package com.imie.money.manager;

import com.imie.money.model.Money;

public class Converter {

    public Money add(Money m1, Money m2) {

        if(!m1.getCurrency().equals(m2.getCurrency())) {
            throw new UnsupportedOperationException("Currency conversion not available yet");
        }
        return new Money(m1.getCurrency(), m1.getAmount() + m2.getAmount());
    }
}
