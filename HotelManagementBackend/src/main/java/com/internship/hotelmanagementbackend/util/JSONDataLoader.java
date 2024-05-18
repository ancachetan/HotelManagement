package com.internship.hotelmanagementbackend.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.internship.hotelmanagementbackend.model.Hotel;
import com.internship.hotelmanagementbackend.model.Room;
import com.internship.hotelmanagementbackend.model.RoomType;
import com.internship.hotelmanagementbackend.repository.HotelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class JSONDataLoader implements CommandLineRunner {
    private static final String JSON_FILE_NAME = "Hotels.json";

    private final HotelRepository hotelRepository;

    public JSONDataLoader(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (hotelRepository.count() > 0) {
            System.out.println("Data already loaded");
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        InputStream inputStream = new ClassPathResource(JSON_FILE_NAME).getInputStream();
        List<Hotel> hotels = mapper.readValue(inputStream, new TypeReference<>() {
        });
        for (Hotel hotel : hotels) {
            for (Room room : hotel.getRooms()) {
                room.setType(RoomType.fromValue(room.getType().getValue()));
                room.setHotel(hotel);
            }
            hotelRepository.save(hotel);
        }
    }
}
