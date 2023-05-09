package com.tech.TechShopAPI.dto;

import com.tech.TechShopAPI.model.Category;
import com.tech.TechShopAPI.model.Feedback;
import com.tech.TechShopAPI.model.Image;
import com.tech.TechShopAPI.model.Label;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Builder
public class ProductDto {
    private int id;
    private String name;
    private boolean active;
    private String category;
    private int price;
    private LabelDto label;
    private int quantity;
    private String decription;
    List<Image> images ;

    List<FeedbackDto> feedbacks ;
}
