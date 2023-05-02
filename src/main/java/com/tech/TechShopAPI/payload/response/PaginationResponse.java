package com.tech.TechShopAPI.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
    public class PaginationResponse<T> {
    private int last_visible_page;
    private boolean has_next_page;
    private int current_page;
    private int count;
    private int total;
    private int per_page;
    private List<T> data;

}
