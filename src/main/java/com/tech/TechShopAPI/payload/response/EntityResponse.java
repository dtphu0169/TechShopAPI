package com.tech.TechShopAPI.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EntityResponse {
    Long orderId;
    int amount;
    String description;
//    String bankCode;
}
