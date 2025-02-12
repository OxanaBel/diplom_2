package ru.shumova.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.shumova.praktikum.dto.OrderCreateRequest;
import ru.shumova.praktikum.dto.UserLoginRequest;
import ru.shumova.praktikum.service.OrderService;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static ru.shumova.praktikum.config.TestData.*;

public class OrderCreateTest extends AbstractTest {
    OrderService orderService = new OrderService();

    @Before
    public void init() {
        createUser();
        token = userService.loginUser(new UserLoginRequest(EMAIL, PASSWORD)).then().extract().body().path("accessToken");
    }

    @Test
    @DisplayName("Создание заказа с авторизацией")
    public void orderCreateTest() {
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest(INGREDIENTS);
        orderService.createOrder(orderCreateRequest, token).then().statusCode(200).body("success", is(true));
    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    public void orderCreateWithoutLoginTest() {
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest(INGREDIENTS);
        orderService.createOrder(orderCreateRequest, StringUtils.EMPTY).then().statusCode(200).body("success", is(true));
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void orderCreateWithoutIngredientsTest() {
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest(List.of());
        orderService.createOrder(orderCreateRequest, token).then().statusCode(400).body("success", is(false), "message", is("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Создание заказа с неверным хешем ингредиентов")
    public void orderCreateIncorrectHashIngredientsTest() {
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest(List.of("Булка", "Соус", "Котлета", "Булка"));
        orderService.createOrder(orderCreateRequest, token).then().statusCode(500);
    }

    @After
    public void after() {
        deleteUser();
    }
}
