package com.imie.money.manager;

import com.imie.money.model.Money;
import com.imie.money.model.MoneyWsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Component
@RestController
public class Converter {

    @Autowired
    private CurrencyRate currencyRate;

    @PostMapping(path = "add")
    public Money addWs(@Valid @RequestBody(required = true) MoneyWsParam body) {

        return add(body.getM1(), body.getM2());
    }

    @PostMapping(path = "sub")
    public Money subWs(@Valid @RequestBody(required = true) MoneyWsParam body) {

        return sub(body.getM1(), body.getM2());
    }

    public Money add(Money m1, Money m2) {

        return computeOperation(Operator.ADDITION, m1, m2);
    }

    public Money sub(Money m1, Money m2) {

        return computeOperation(Operator.SUBTRACTION, m1, m2);
    }

    protected Money computeOperation(Operator op, Money m1, Money m2) {

        if(m1==null || m2==null) {
            throw new IllegalArgumentException("Money should not be null");
        }

        if(m1.getAmount() < 0 || m2.getAmount() < 0) {
            throw new IllegalArgumentException("Amounts must be positive figures");
        }

        if(!m1.getCurrency().equals(m2.getCurrency())) {
            return new Money(m1.getCurrency(), op.apply(
                    m1.getAmount(),
                    m2.getAmount() * currencyRate.getCurrencyRate(m1.getCurrency(),
                            m2.getCurrency())));
        }

        return new Money(m1.getCurrency(), op.apply(m1.getAmount(), m2.getAmount()));
    }
}