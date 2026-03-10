package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;
import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.order = order;
    }

    public Payment(String id, String method, String status, Map<String, String> paymentData, Order order) {
        this.id = id;
        this.method = method;
        this.status = status;
        this.paymentData = paymentData;
        this.order = order;
    }
}
