package com.jozwik.empik.github;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GithubClientTest {

    @Mock
    private WebClient webClient;
    @InjectMocks
    private GithubClient githubClient;

    @Mock
    private WebClient.RequestHeadersUriSpec uriSpec;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private Mono<GithubUser> user;

    @Test
    void shouldReturnUser() {
        //given
        final String login = "mojombo";
        given(webClient.get()).willReturn(uriSpec);
        given(uriSpec.uri("/users/" + login)).willReturn(requestHeadersSpec);
        given(requestHeadersSpec.retrieve()).willReturn(responseSpec);
        given(responseSpec.bodyToMono(GithubUser.class)).willReturn(user);

        //when
        final Mono<GithubUser> result = githubClient.getUserByLogin(login);

        //then
        assertThat(result).isEqualTo(user);
    }
}
