package com.openbet.play.paymentgatewayapi.controller;

import com.openbet.play.paymentgateway.core.model.Country;
import com.openbet.play.paymentgateway.core.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping()
    public List<Country> getAll() {
        return countryService.getAll();
    }
}
