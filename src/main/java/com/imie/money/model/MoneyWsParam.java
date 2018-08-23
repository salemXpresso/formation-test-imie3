package com.imie.money.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
public class MoneyWsParam {

    @NotNull
    private Money m1;
    @NotNull
    private Money m2;

    public Money getM1() {
        return m1;
    }

    public void setM1(Money m1) {
        this.m1 = m1;
    }

    public Money getM2() {
        return m2;
    }

    public void setM2(Money m2) {
        this.m2 = m2;
    }
}
