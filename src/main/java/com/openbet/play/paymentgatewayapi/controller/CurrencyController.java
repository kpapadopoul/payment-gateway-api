package com.openbet.play.paymentgatewayapi.controller;

import com.openbet.play.paymentgateway.core.model.Currency;
import com.openbet.play.paymentgateway.core.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping()
    public List<Currency> getAll() {
        return currencyService.getAll();
    }
}
