package com.internship.hotelmanagementbackend.config;

import com.internship.hotelmanagementbackend.dto.BookingRequestDto;
import com.internship.hotelmanagementbackend.dto.BookingWithDetailsDto;
import com.internship.hotelmanagementbackend.dto.FeedbackRequestDto;
import com.internship.hotelmanagementbackend.model.Booking;
import com.internship.hotelmanagementbackend.model.Feedback;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Booking, BookingRequestDto>() {
            @Override
            protected void configure() {
                map().setRoomId(source.getRoom().getId());
                map().setUserId(source.getUser().getId());
            }
        });
        modelMapper.addMappings(new PropertyMap<BookingRequestDto, Booking>() {
            @Override
            protected void configure() {
                map().setId(null);
            }
        });
        modelMapper.addMappings(new PropertyMap<FeedbackRequestDto, Feedback>() {
            @Override
            protected void configure() {
                map().setId(null);
            }
        });
        modelMapper.addMappings(new PropertyMap<Booking, BookingWithDetailsDto>() {
            @Override
            protected void configure() {
                map().setRoomType(source.getRoom().getType());
                map().setHotelName(source.getRoom().getHotel().getName());
                map().setHotelId(source.getRoom().getHotel().getId());
                map().setPrice(source.getRoom().getPrice());
            }
        });
        return modelMapper;
    }
}
