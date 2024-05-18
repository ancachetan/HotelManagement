package com.internship.hotelmanagementbackend.service.impl;

import com.internship.hotelmanagementbackend.dto.FeedbackDto;
import com.internship.hotelmanagementbackend.dto.FeedbackRequestDto;
import com.internship.hotelmanagementbackend.exception.ResourceNotFoundException;
import com.internship.hotelmanagementbackend.model.Feedback;
import com.internship.hotelmanagementbackend.model.Hotel;
import com.internship.hotelmanagementbackend.model.User;
import com.internship.hotelmanagementbackend.repository.FeedbackRepository;
import com.internship.hotelmanagementbackend.repository.HotelRepository;
import com.internship.hotelmanagementbackend.repository.UserRepository;
import com.internship.hotelmanagementbackend.service.FeedbackService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.internship.hotelmanagementbackend.util.ExceptionMessages.HOTEL_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.internship.hotelmanagementbackend.util.ExceptionMessages.USER_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, UserRepository userRepository, HotelRepository hotelRepository, ModelMapper modelMapper) {
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FeedbackDto add(FeedbackRequestDto feedbackRequestDto) {
        Feedback feedback = modelMapper.map(feedbackRequestDto, Feedback.class);
        Hotel hotel = hotelRepository.findById(feedbackRequestDto.getHotelId())
                .orElseThrow(() -> new ResourceNotFoundException(HOTEL_NOT_FOUND_EXCEPTION_MESSAGE.formatted(feedbackRequestDto.getHotelId())));
        User user = userRepository.findById(feedbackRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE.formatted(feedbackRequestDto.getHotelId())));
        feedback.setHotel(hotel);
        feedback.setUser(user);
        Feedback savedFeedback = feedbackRepository.save(feedback);
        hotel.getFeedbacks().add(savedFeedback);
        user.getFeedbacks().add(savedFeedback);
        return modelMapper.map(savedFeedback, FeedbackDto.class);
    }
}
