package com.jozwik.empik.users;

import com.jozwik.empik.exceptions.MissingUserException;
import com.jozwik.empik.model.ErrorWsm;
import com.jozwik.empik.model.UserWsm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UsersController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/{login}")
    public ResponseEntity<UserWsm> getUsers(@PathVariable("login") String login) {
        final UserWsm user = usersService.getUser(login);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ExceptionHandler(MissingUserException.class)
    public ResponseEntity<ErrorWsm> handleMissingUserException(MissingUserException ex) {
        LOGGER.error("Missing user {}", ex.getMessage());
        ErrorWsm error = new ErrorWsm("Missing user", "User not found", null, null);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorWsm> handleUnexpectedException(Exception ex, HttpServletRequest req) {
        LOGGER.error("{} {} failed", req.getMethod(), req.getRequestURI(), ex);
        ErrorWsm error = new ErrorWsm("Operation failed", "Please try again later", null, null);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
