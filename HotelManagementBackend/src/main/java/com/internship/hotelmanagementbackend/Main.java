package com.internship.hotelmanagementbackend;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now();
        System.out.println(date1.compareTo(date2) >0);
    }
}
