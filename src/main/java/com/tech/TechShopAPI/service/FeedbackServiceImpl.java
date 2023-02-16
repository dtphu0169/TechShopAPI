package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.FeedbackDto;
import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.model.Feedback;
import com.tech.TechShopAPI.repository.AccountRepository;
import com.tech.TechShopAPI.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class FeedbackServiceImpl implements FeedbackService{
    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public void save(Principal principal, FeedbackDto feedbackDto) {
        Account account = accountRepository.getbyEmail(principal.getName()).get();

        Feedback feedback = new Feedback();
        feedback.setProductId(feedbackDto.getProductId());
        feedback.setAccount(account);
        feedback.setComment(feedbackDto.getComment());
        feedback.setStar(feedbackDto.getStar());
        feedbackRepository.save(feedback);

    }
}
