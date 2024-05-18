package com.internship.hotelmanagementbackend.controller.impl;

import com.internship.hotelmanagementbackend.controller.FeedbackController;
import com.internship.hotelmanagementbackend.dto.FeedbackDto;
import com.internship.hotelmanagementbackend.dto.FeedbackRequestDto;
import com.internship.hotelmanagementbackend.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FeedbackControllerImpl implements FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackControllerImpl(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Override
    public ResponseEntity<FeedbackDto> add(FeedbackRequestDto feedbackRequestDto) {
        return new ResponseEntity<>(feedbackService.add(feedbackRequestDto), HttpStatus.CREATED);
    }
}
