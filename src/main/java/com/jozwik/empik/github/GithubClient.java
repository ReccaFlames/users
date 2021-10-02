package com.jozwik.empik.github;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GithubClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(GithubClient.class);

    private final WebClient webClient;

    public GithubClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<GithubUser> getUserByLogin(String login) {
        LOGGER.debug("Retrieving user info from github api");
        return webClient.get()
                .uri("/users/" + login)
                .retrieve()
                .bodyToMono(GithubUser.class);
    }
}
