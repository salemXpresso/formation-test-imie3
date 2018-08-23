package com.imie.money.manager;

import com.imie.money.model.Money;
import com.imie.money.model.MoneyWsParam;
import com.imie.money.ws.WsExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Currency;

@Component
@RestController
public class Converter {

    @Autowired
    private CurrencyRate currencyRate;

    @PostMapping(path = "add")
    public Money addWs(@Valid @RequestBody(required = true) MoneyWsParam body) {

        return add(body.getM1(), body.getM2());
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
                    m2.getAmount() * currencyRate.getCurrencyRate(m1.getCurrency(),
                            m2.getCurrency()));
        }

        return new Money(m1.getCurrency(), m1.getAmount() + m2.getAmount());
    }

}