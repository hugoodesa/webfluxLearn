package br.com.stapassoli.webfluxSpringLearn;

import br.com.stapassoli.webfluxSpringLearn.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebfluxSpringLearnApplicationTests {

    @Autowired
    private WebTestClient webClient;

    private User createUser() {
        return User
                .builder()
                .id(null)
                .name("hugoodesa")
                .password("fired")
                .build();
    }

    @Test
    void contextLoads() {

        User user = createUser();

        webClient
			.method(HttpMethod.POST).uri("/users")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON).bodyValue(user)
			.exchange()
			.expectStatus().is2xxSuccessful()
			.expectBody(User.class)
			.value(postUser -> {
				assertNotNull(postUser.getId());
				assertEquals(postUser.getName(), user.getName());
				assertEquals(postUser.getPassword(), user.getPassword());
			});

        webClient
			.method(HttpMethod.GET)
			.uri("/users")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON).bodyValue(user)
			.exchange()
			.expectStatus()
			.is2xxSuccessful()
			.expectBodyList(User.class)
			.value(users -> {

				for (User currentUser : users) {
					assertNotNull(currentUser.getId());
					assertEquals(currentUser.getName(), user.getName());
					assertEquals(currentUser.getPassword(), user.getPassword());
				}

			});
    }

}
