package com.jozwik.empik.users;

import com.jozwik.empik.calculations.CalculationsService;
import com.jozwik.empik.exceptions.MissingUserException;
import com.jozwik.empik.github.GithubClient;
import com.jozwik.empik.github.GithubUser;
import com.jozwik.empik.model.UserWsm;
import com.jozwik.empik.persistance.LoginEventPersister;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    private static final String LOGIN = "mojombo";
    private static final String NAME = "Tom Preston-Werner";
    private static final String TYPE = "User";
    private static final Instant CREATED_AT = Instant.parse("2007-10-20T05:24:19Z");
    private static final int FOLLOWERS = 11;
    private static final int PUBLIC_REPOS = 62;
    private static final float CALCULATIONS = 0.016915554f;

    @Mock
    private GithubClient client;
    @Mock
    private CalculationsService calculationsService;
    @Mock
    private LoginEventPersister loginEventPersister;
    @InjectMocks
    private UsersService usersService;


    @Test
    void shouldReturnUser() throws MalformedURLException {
        //given
        URL avatarUrl = new URL("https://avatars.githubusercontent.com/u/1?v=4");
        Mono<GithubUser> userMono = Mono.justOrEmpty(createGithubUser(avatarUrl));
        given(client.getUserByLogin(LOGIN)).willReturn(userMono);
        given(calculationsService.calculate(FOLLOWERS, PUBLIC_REPOS)).willReturn(CALCULATIONS);
        UserWsm expected = new UserWsm(1L, LOGIN, NAME, TYPE, avatarUrl, CREATED_AT, CALCULATIONS);

        //when
        final UserWsm result = usersService.getUser(LOGIN);

        //then
        assertThat(result).isEqualTo(expected);
        then(loginEventPersister).should().persist(LOGIN);
    }

    @Test
    void shouldThrowMissingUserException() {
        //given
        Mono<GithubUser> userMono = Mono.empty();
        given(client.getUserByLogin(LOGIN)).willReturn(userMono);

        //when
        assertThatThrownBy(() -> usersService.getUser(LOGIN))
                //then
                .isInstanceOf(MissingUserException.class)
                .hasMessage("User with " + LOGIN + " not found");
        then(calculationsService).shouldHaveNoInteractions();
        then(loginEventPersister).should().persist(LOGIN);
    }

    private GithubUser createGithubUser(URL avatarUrl) {
        return new GithubUser(1L, LOGIN, NAME, avatarUrl, TYPE, FOLLOWERS, PUBLIC_REPOS, CREATED_AT);
    }
}
