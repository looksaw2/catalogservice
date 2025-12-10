package org.example.catalogservice.web;


import org.example.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureRestTestClient
public class BookCtrollerTests {
    @Autowired
    private RestTestClient restTestClient;

    @Test
    void whenPostRequestThenBookCreated() {
        var book = new Book(
                "1231231231",
                "Title",
                "Author",
                9.90
        );
        restTestClient
                .post()
                .uri("/books")
                .body(book)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(actualBook -> {
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook.isbn()).isEqualTo(book.isbn());
                });

    }
}
