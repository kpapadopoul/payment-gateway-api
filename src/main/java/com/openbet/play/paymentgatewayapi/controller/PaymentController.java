package com.openbet.play.paymentgatewayapi.controller;

import com.openbet.play.paymentgateway.payments.model.Payment;
import com.openbet.play.paymentgateway.payments.model.PaymentStatus;
import com.openbet.play.paymentgateway.payments.model.parameters.CancelPaymentParametersBuilder;
import com.openbet.play.paymentgateway.payments.model.parameters.CompletePaymentParametersBuilder;
import com.openbet.play.paymentgateway.payments.model.parameters.DepositParameters;
import com.openbet.play.paymentgateway.payments.model.parameters.UpdatePaymentParametersBuilder;
import com.openbet.play.paymentgateway.payments.model.parameters.WithdrawParameters;
import com.openbet.play.paymentgateway.payments.model.responses.CancelPaymentResponse;
import com.openbet.play.paymentgateway.payments.model.responses.CompletePaymentResponse;
import com.openbet.play.paymentgateway.payments.model.responses.DepositResponse;
import com.openbet.play.paymentgateway.payments.model.responses.UpdatePaymentResponse;
import com.openbet.play.paymentgateway.payments.model.responses.WithdrawResponse;
import com.openbet.play.paymentgateway.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping()
    public List<Payment> getHistory() {
        return paymentService.getHistory();
    }

    @GetMapping("/{paymentRef}")
    public ResponseEntity<Payment> getPaymentInfo(@PathVariable String paymentRef) {
        return ResponseEntity.ok(paymentService.getPaymentInfo(paymentRef));
    }

    @PostMapping("/deposit")
    public ResponseEntity<Payment> deposit(@RequestBody DepositParameters depositParameters) {
        final DepositResponse depositResponse = paymentService.deposit(depositParameters);

        if (depositResponse.isSuccess())
            return ResponseEntity.ok(depositResponse.getPayment());
        else
            return ResponseEntity.internalServerError().build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Payment> withdraw(@RequestBody WithdrawParameters withdrawParameters) {
        final WithdrawResponse withdrawResponse = paymentService.withdraw(withdrawParameters);

        if (withdrawResponse.isSuccess())
            return ResponseEntity.ok(withdrawResponse.getPayment());
        else
            return ResponseEntity.internalServerError().build();
    }

    @PatchMapping("/{paymentRef}/complete")
    public ResponseEntity<Payment> completePayment(@PathVariable String paymentRef) {
        final CompletePaymentResponse completePaymentResponse = paymentService.completePayment(
                CompletePaymentParametersBuilder.aCompletePaymentParameters()
                        .setTransactionId(paymentRef).build());

        if (completePaymentResponse.isSuccess())
            return ResponseEntity.ok(completePaymentResponse.getPayment());
        else
            return ResponseEntity.internalServerError().build();
    }

    @PatchMapping("/{paymentRef}/update")
    public ResponseEntity<Payment> updatePayment(
            @PathVariable String paymentRef,
            @RequestParam(value = "paymentStatus") String paymentStatus) {
        final UpdatePaymentResponse updatePaymentResponse = paymentService.updatePayment(
                UpdatePaymentParametersBuilder.anUpdatePaymentParameters()
                        .setTransactionId(paymentRef)
                        .setPaymentStatus(PaymentStatus.valueOf(paymentStatus))
                        .build());

        if (updatePaymentResponse.isSuccess())
            return ResponseEntity.ok(updatePaymentResponse.getPayment());
        else
            return ResponseEntity.internalServerError().build();
    }

    @PatchMapping("/{paymentRef}/cancel")
    public ResponseEntity<Payment> cancelPayment(@PathVariable String paymentRef) {
        final CancelPaymentResponse cancelPaymentResponse = paymentService.cancelPayment(
                CancelPaymentParametersBuilder.aCancelPaymentParameters()
                        .setTransactionId(paymentRef).build());

        if (cancelPaymentResponse.isSuccess())
            return ResponseEntity.ok(cancelPaymentResponse.getPayment());
        else
            return ResponseEntity.internalServerError().build();
    }

}
