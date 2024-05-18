package com.internship.hotelmanagementbackend.model;

import lombok.Getter;

@Getter
public enum RoomType {
    SINGLE(1),
    DOUBLE(2),
    SUITE(3),
    MATRIMONIAL(4);

    private final int value;

    RoomType(int value) {
        this.value = value;
    }

    public static RoomType fromValue(int value) {
        for (RoomType type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown room type value: " + value);
    }
}
