package com.tech.TechShopAPI.dto;

import com.tech.TechShopAPI.model.*;

import java.util.ArrayList;
import java.util.List;

public class Dtomapper {
    public static CategoryDto mapCategory(Category category){
        CategoryDto result = new CategoryDto();
        result.setId(category.getId());
        result.setName(category.getName());
        result.setImage_path(category.getImage_path());
        return result;
    }

    public static CartproductDto mapCartproduct(Cartproduct cartproduct){
        ProductDto productDto = mapProduct(cartproduct.getProduct());

        CartproductDto result = CartproductDto.builder()
                .product(productDto)
                .quantity(cartproduct.getQuantity())
                .build();
        return result;
    }

    public static AccountDto mapAccount(Account account){
        List<AddressDto> addressDtoList = new ArrayList<>();
        if (account.getAddressList() != null){
            for(Address address : account.getAddressList()){
                AddressDto dto = mapAddress(address);
                addressDtoList.add(dto);
            }
        }

        AccountDto result = AccountDto.builder()
                .id(account.getId())
                .active(account.isActive())
                .email(account.getEmail())
                .phone(account.getPhone())
                .role(account.getRole())
                .registerDate(account.getRegisterDate())
                .userName(account.getUserName())
                .addressList(addressDtoList)
                .build();
        return result;
    }

    public static FeedbackDto mapFeedback(Feedback feedback){
        FeedbackDto result = new FeedbackDto();
        result.setUserName(feedback.getAccount().getUserName());
        result.setProduct(feedback.getProduct().getName());
        result.setComment(feedback.getComment());
        result.setStar(feedback.getStar());
        return result;
    }
    public static List<FeedbackDto> mapFeedbackList(List<Feedback> feedbackList){
        List<FeedbackDto> feedbackDtoList = new ArrayList<>();
        for(Feedback feedback : feedbackList){
            feedbackDtoList.add(mapFeedback(feedback));
        }
        return feedbackDtoList;
    }


    public static AddressDto mapAddress(Address address){
        AddressDto result = AddressDto.builder()
                .id(address.getId())
                .provine_city(address.getProvine_city())
                .dictrict(address.getDictrict())
                .ward(address.getWard())
                .detail(address.getDetail())
                .build();
        return result;
    }

    public static ProductDto mapProduct(Product product){
        LabelDto label = mapLabel(product.getLabel());

        ProductDto result = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .active(product.isActive())
                .category(product.getCategory().getName())
                .price(product.getPrice())
                .label(label)
                .quantity(product.getQuantity())
                .decription(product.getDecription())
                .feedbacks(mapFeedbackList(product.getFeedbacks()))
                .images(product.getImages())
                .build();
        return result;
    }
    public static Product remapProduct(ProductDto dto){
        Product result = Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .active(dto.isActive())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .decription(dto.getDecription())
                .build();
        return result;
    }
    public static List<ProductDto> mapProductList(List<Product> productList){
        List<ProductDto> products = new ArrayList<>();
        for (Product product : productList){
            products.add(Dtomapper.mapProduct(product));
        }
        return products;
    }


    public static LabelDto mapLabel(Label label){
        if (label == null){
            return null;
        }
        LabelDto dto = LabelDto.builder()
                .id(label.getId())
                .name(label.getName())
                .price_rate(label.getPrice_rate())
                .build();
        return dto;
    }
}
