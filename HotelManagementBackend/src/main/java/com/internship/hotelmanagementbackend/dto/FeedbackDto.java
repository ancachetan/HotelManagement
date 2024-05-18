package com.internship.hotelmanagementbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FeedbackDto {
    private Long id;
    private String comment;
    private Long hotelId;
    private Long userId;
}
