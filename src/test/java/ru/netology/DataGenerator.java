package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {}

        public static RegistrationByCardInfo generateByCard(String locale) {
            Faker facer = new Faker(new Locale("ru"));
            return new RegistrationByCardInfo(
                    facer.address().city(),
                    facer.name().fullName(),
                    facer.phoneNumber()
            );
        }
        public static String generateDate() {
            LocalDate now = LocalDate.now().plusDays(3);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return now.format(formatter);
        }

    }
}
