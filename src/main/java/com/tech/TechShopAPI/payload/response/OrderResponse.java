package com.tech.TechShopAPI.payload.response;

import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.model.Bill_detail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private long id;
    private String customerName;
    private Date datecreate;
    private int price;
    private int shipprice;
    private String address;
    private String phone;
    private boolean paid;
    private String status;
    private String note;
    private List<OrderDetailResponse> billDetails;
}
