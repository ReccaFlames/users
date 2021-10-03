package com.jozwik.empik.persistance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class LoginEventPersisterTest {

    private static final String LOGIN = "mojombo";

    @Mock
    private LoginEventRepository loginEventRepository;
    @InjectMocks
    private LoginEventPersister loginEventPersister;

    @Captor
    private ArgumentCaptor<LoginEvent> loginEventArgumentCaptor;

    @Test
    void shouldPersistNewLoginEvent() {
        //given
        given(loginEventRepository.findByLogin(LOGIN)).willReturn(Optional.empty());

        //when
        loginEventPersister.persist(LOGIN);

        //then
        then(loginEventRepository).should().save(loginEventArgumentCaptor.capture());
        final LoginEvent loginEvent = loginEventArgumentCaptor.getValue();
        assertThat(loginEvent.getLogin()).isEqualTo(LOGIN);
        assertThat(loginEvent.getRequestCount()).isEqualTo(1);
    }

    @Test
    void shouldUpdateLoginEvent() {
        //given
        UUID loginId = UUID.fromString("94709da8-f25d-4f48-8e5c-53f6858e3864");
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setLoginId(loginId);
        loginEvent.setLogin(LOGIN);
        loginEvent.setRequestCount(1L);
        given(loginEventRepository.findByLogin(LOGIN)).willReturn(Optional.of(loginEvent));

        //when
        loginEventPersister.persist(LOGIN);

        //then
        then(loginEventRepository).should().save(loginEventArgumentCaptor.capture());
        final LoginEvent eventValue = loginEventArgumentCaptor.getValue();
        assertThat(eventValue.getLoginId()).isEqualTo(loginId);
        assertThat(eventValue.getLogin()).isEqualTo(LOGIN);
        assertThat(eventValue.getRequestCount()).isEqualTo(2);
    }
}
