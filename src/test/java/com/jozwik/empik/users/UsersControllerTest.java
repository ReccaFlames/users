package com.jozwik.empik.users;

import com.jozwik.empik.exceptions.MissingUserException;
import com.jozwik.empik.model.ErrorWsm;
import com.jozwik.empik.model.UserWsm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

    private static final String LOGIN = "mojombo";

    @Mock
    private UsersService usersService;
    @InjectMocks
    private UsersController usersController;

    @Mock
    private UserWsm user;

    @Test
    void shouldReturnUserData() {
        //given
        given(usersService.getUser(LOGIN)).willReturn(user);

        //when
        final ResponseEntity<UserWsm> result = usersController.getUsers(LOGIN);

        //then
        assertThat(result.getBody()).isEqualTo(user);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldHandleMissingUserException() {
        //given
        MissingUserException ex = new MissingUserException("User with " + LOGIN + " not found");
        ErrorWsm error = new ErrorWsm("Missing user", "User not found", null, null);

        //when
        ResponseEntity<ErrorWsm> result = usersController.handleMissingUserException(ex);

        //then
        assertThat(result.getBody()).isEqualTo(error);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldHandleUnexpectedException() {
        //given
        RuntimeException ex = new RuntimeException("Unexpected exception");
        HttpServletRequest req = mock(HttpServletRequest.class);
        ErrorWsm error = new ErrorWsm("Operation failed", "Please try again later", null, null);

        //when
        ResponseEntity<ErrorWsm> result = usersController.handleUnexpectedException(ex, req);

        //then
        assertThat(result.getBody()).isEqualTo(error);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
