package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.FeedbackDto;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public interface FeedbackService {
    void save(Principal principal, FeedbackDto feedbackDto);
}
