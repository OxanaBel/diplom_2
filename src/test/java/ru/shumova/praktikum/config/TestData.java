package ru.shumova.praktikum.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

@Getter
@Setter
public class TestData {
    public static final String EMAIL = RandomStringUtils.randomAlphabetic(15) + "@yandex.ru";
    public static final String PASSWORD = RandomStringUtils.randomAlphabetic(10);
    public static final String NAME = RandomStringUtils.randomAlphabetic(10);
    public static final String INCORRECT_EMAIL = RandomStringUtils.randomAlphabetic(15) + "@yandex.ru";
    public static final String INCORRECT_PASSWORD = RandomStringUtils.randomAlphabetic(10);
    public static final String EMAIL_UPDATE = RandomStringUtils.randomAlphabetic(15) + "@yandex.ru";
    public static final String NAME_UPDATE = RandomStringUtils.randomAlphabetic(10);
    public static final List<String> INGREDIENTS = List.of(
            "61c0c5a71d1f82001bdaaa6d",
            "61c0c5a71d1f82001bdaaa72",
            "61c0c5a71d1f82001bdaaa70",
            "61c0c5a71d1f82001bdaaa6d"
    );
}
