package com.imie.money.manager;

import com.imie.money.model.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class Converter {

    @Autowired
    private CurrencyRate currencyRate;

    @RequestMapping(method= RequestMethod.POST)
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