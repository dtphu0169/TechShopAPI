package com.tech.TechShopAPI.dto;

import com.tech.TechShopAPI.model.Account;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
public class FeedbackDto {
    private String userName;
    private String product;
    private String comment;
    private int star;
}
