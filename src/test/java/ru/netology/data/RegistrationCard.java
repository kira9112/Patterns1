package ru.netology.data;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class RegistrationCard {
    private final String city;
    private final String name;
    private final String phone;
}