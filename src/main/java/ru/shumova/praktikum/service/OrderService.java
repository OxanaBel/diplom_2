package ru.shumova.praktikum.service;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.shumova.praktikum.dto.OrderCreateRequest;

import static ru.shumova.praktikum.util.RequestHelper.API_ORDERS;
import static ru.shumova.praktikum.util.RequestHelper.getRequestSpec;

public class OrderService {

    @Step("Создание заказа")
    public Response createOrder(OrderCreateRequest request, String token) {
        return getRequestSpec().header("authorization", token).body(request).when().post(API_ORDERS);
    }

    @Step("Получение заказов конкретного пользователя")
    public Response getOrder(String token) {
        return getRequestSpec().header("authorization", token).when().get(API_ORDERS);
    }

}
