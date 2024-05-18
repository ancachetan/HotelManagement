package com.internship.hotelmanagementbackend.service;

import com.internship.hotelmanagementbackend.dto.FeedbackDto;
import com.internship.hotelmanagementbackend.dto.FeedbackRequestDto;

public interface FeedbackService {
    FeedbackDto add(FeedbackRequestDto feedbackRequestDto);
}
