package com.junit5.service;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class apiAssertDemo {
    @Test
    public void xmlDemo(){
        given()
                .get("http://127.0.0.1:8001/test.xml")
                .then()
                .body("shopping.category[0].item[0].name",equalTo("Chocolate"))
                .body("shopping.category.findAll { it.@type == 'groceries' }.item.size()",equalTo(2))
                //学员群同学说下面**匹配的竟然是1，一试还真是，大家都没有头绪，后续有时间可研究研究
//                .body("**.findAll { it.@type == 'groceries' }.item.size()",equalTo(2));
                .body("**.findAll { it.price == 10 }.name",equalTo("Chocolate"))
                .body("shopping.category.item.findAll { it.price == 10 }.name",equalTo("Chocolate"))
        ;
    }

    @Test
    public void jsonDemo(){
        given()
                .get("http://127.0.0.1:8001/mock.json")
                .then()
                .body("store.book.category[0]",equalTo("reference"))
                .body("store.bicycle.color",equalTo("red"))
                //find默认取的是第一个，而findAll取的是所有的
                .body("store.book.find { book -> book.price == 8.95f }.price ",equalTo(8.95f))
                .body("store.book.findAll { book -> book.price >= 10 && book.price <= 15 }.price[0] ",equalTo(12.99f))
        ;
    }
}
