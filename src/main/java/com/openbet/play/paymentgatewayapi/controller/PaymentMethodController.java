package com.openbet.play.paymentgatewayapi.controller;

import com.openbet.play.paymentgateway.payments.model.PaymentMethod;
import com.openbet.play.paymentgateway.payments.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment-methods")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping()
    public List<PaymentMethod> getAll() {
        return paymentMethodService.getAll();
    }
}
