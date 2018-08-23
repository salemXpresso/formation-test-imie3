package com.imie.money.model;

import java.util.Currency;

public class Money {

    private Currency currency;

    private float amount;

    public Money() {
    }

    public Money(Currency currency, float amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public float getAmount() {
        return amount;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
