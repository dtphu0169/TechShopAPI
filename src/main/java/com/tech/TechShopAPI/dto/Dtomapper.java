package com.tech.TechShopAPI.dto;

import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.model.Cartproduct;
import com.tech.TechShopAPI.model.Category;
import com.tech.TechShopAPI.model.Feedback;

public class Dtomapper {
    public static CategoryDto mapCategory(Category category){
        CategoryDto result = new CategoryDto();
        result.setId(category.getId());
        result.setName(category.getName());
        result.setImage_path(category.getImage_path());
        return result;
    }

    public static CartproductDto mapCartproduct(Cartproduct cartproduct){
        CartproductDto result = new CartproductDto();
        result.setProductId(cartproduct.getProduct().getId());
        result.setQuantity(cartproduct.getQuantity());
        return result;
    }

    public static AccountDto mapAccount(Account account){
        AccountDto result = new AccountDto();
        result.setId(account.getId());
        result.setActive(account.isActive());
        result.setEmail(account.getEmail());
        result.setPhone(account.getPhone());
        result.setRole(account.getRole());
        result.setRegisterDate(account.getRegisterDate());
        result.setUserName(account.getUserName());
        return result;
    }

    public static FeedbackDto mapFeedback(Feedback feedback){
        FeedbackDto result = new FeedbackDto();
        result.setUserName(feedback.getAccount().getUserName());
        result.setProductId(feedback.getProductId());
        result.setComment(feedback.getComment());
        result.setStar(feedback.getStar());
        return result;
    }
}
