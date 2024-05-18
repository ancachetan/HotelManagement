package com.internship.hotelmanagementbackend.repository;

import com.internship.hotelmanagementbackend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByRoomIdAndIsCanceledFalse(Long roomId);
    List<Booking> findAllByUserId(Long userId);
}
