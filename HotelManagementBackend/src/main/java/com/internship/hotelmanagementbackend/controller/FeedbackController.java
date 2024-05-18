package com.internship.hotelmanagementbackend.controller;

import com.internship.hotelmanagementbackend.dto.FeedbackDto;
import com.internship.hotelmanagementbackend.dto.FeedbackRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/feedbacks")
public interface FeedbackController {
    @PostMapping()
    ResponseEntity<FeedbackDto> add(@Valid @RequestBody FeedbackRequestDto feedbackRequestDto);
}
