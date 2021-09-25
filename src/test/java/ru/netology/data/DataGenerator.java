package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data

public class DataGenerator {

    private DataGenerator() {
    }

    public static String generateDate(int days) {
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String city = faker.options().option("Москва", "Санкт-Петербург", "Самара", "Екатеринбург", "Казань", "Орёл", "Хабаровск");
        return city;
    }



    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().fullName();
        return name;
    }



    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.numerify("+79#########");
        return phone;
    }


    public static class Registration {

        private Registration() {
        }

        public static RegistrationCard generateUser(String locale) {
            RegistrationCard user = new RegistrationCard(generateCity(locale), generateName(locale), generatePhone(locale));
            return (RegistrationCard) user;
        }
    }
}












