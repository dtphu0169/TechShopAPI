package com.tech.TechShopAPI.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {
    String status;
    String message;
    String url;
}
