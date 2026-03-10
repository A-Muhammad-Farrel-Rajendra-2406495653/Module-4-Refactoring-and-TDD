package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentTest {

    @Test
    void testCreatePaymentDefaultStatus() {
        Order order = new Order("id-123", new ArrayList<>(), 1708560000L, "User");
        Map<String, String> paymentData = Map.of("card_number", "12345678");

        Payment payment = new Payment("pay-001", "BANK_TRANSFER", paymentData, order);

        Assertions.assertEquals("pay-001", payment.getId());
        Assertions.assertEquals("BANK_TRANSFER", payment.getMethod());
        Assertions.assertEquals(paymentData, payment.getPaymentData());
        Assertions.assertEquals(order, payment.getOrder());
        Assertions.assertEquals("WAITING_PAYMENT", payment.getStatus());
    }

    @Test
    void testSetInvalidStatus() {
        Order order = new Order("id-123", new ArrayList<>(), 1708560000L, "User");
        Payment payment = new Payment("pay-001", "CASH", new HashMap<>(), order);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
    }

    @Test
    void testSetValidStatus() {
        Order order = new Order("id-123", new ArrayList<>(), 1708560000L, "User");
        Payment payment = new Payment("pay-001", "CASH", new HashMap<>(), order);

        payment.setStatus("SUCCESS");
        Assertions.assertEquals("SUCCESS", payment.getStatus());

        payment.setStatus("REJECTED");
        Assertions.assertEquals("REJECTED", payment.getStatus());
    }
}
