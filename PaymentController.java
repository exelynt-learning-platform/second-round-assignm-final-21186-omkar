package com.example.escontroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping("/pay")
    public String pay() {
        return "Payment successful (mock)";
    }}
