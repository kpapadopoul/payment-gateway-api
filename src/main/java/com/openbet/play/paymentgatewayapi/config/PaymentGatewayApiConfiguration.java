package com.openbet.play.paymentgatewayapi.config;

import com.openbet.play.paymentgateway.core.repository.CountryRepository;
import com.openbet.play.paymentgateway.core.repository.CurrencyRepository;
import com.openbet.play.paymentgateway.core.service.CountryService;
import com.openbet.play.paymentgateway.core.service.CurrencyService;
import com.openbet.play.paymentgateway.core.service.HelloService;
import com.openbet.play.paymentgateway.gateways.GatewayFactory;
import com.openbet.play.paymentgateway.payments.repository.PaymentMethodRepository;
import com.openbet.play.paymentgateway.payments.repository.PaymentRepository;
import com.openbet.play.paymentgateway.payments.service.PaymentMethodService;
import com.openbet.play.paymentgateway.payments.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentGatewayApiConfiguration {

    @Bean
    public HelloService helloService(){
        return new HelloService();
    }

    @Bean
    public CountryRepository countryRepository() {
        return new CountryRepository();
    }

    @Bean
    public CountryService countryService(CountryRepository countryRepository) {
        return new CountryService(countryRepository);
    }

    @Bean
    public CurrencyRepository currencyRepository() {
        return new CurrencyRepository();
    }

    @Bean
    public CurrencyService currencyService(CurrencyRepository currencyRepository) {
        return new CurrencyService(currencyRepository);
    }

    @Bean
    public PaymentMethodRepository paymentMethodRepository() {
        return new PaymentMethodRepository();
    }

    @Bean
    public PaymentMethodService paymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        return new PaymentMethodService(paymentMethodRepository);
    }

    @Bean
    public GatewayFactory gatewayFactory() {
        return new GatewayFactory();
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new PaymentRepository();
    }

    @Bean
    public PaymentService paymentService(PaymentRepository paymentRepository) {
        return new PaymentService(paymentRepository, gatewayFactory());
    }

}
