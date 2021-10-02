package com.jozwik.empik.users;

import com.jozwik.empik.calculations.CalculationsService;
import com.jozwik.empik.exceptions.MissingUserException;
import com.jozwik.empik.github.GithubClient;
import com.jozwik.empik.github.GithubUser;
import com.jozwik.empik.model.UserWsm;
import com.jozwik.empik.persistance.LoginEventPersister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersService.class);

    private final GithubClient client;
    private final CalculationsService calculationsService;
    private final LoginEventPersister loginEventPersister;

    public UsersService(GithubClient client, CalculationsService calculationsService, LoginEventPersister loginEventPersister) {
        this.client = client;
        this.calculationsService = calculationsService;
        this.loginEventPersister = loginEventPersister;
    }

    UserWsm getUser(String login) {
        LOGGER.info("Accessing data for user with login {}", login);
        loginEventPersister.persist(login);
        return client.getUserByLogin(login)
                .blockOptional()
                .map(this::convertGithubUserToUserDto)
                .orElseThrow(() -> new MissingUserException("User with " + login + " not found"));
    }

    private UserWsm convertGithubUserToUserDto(GithubUser user) {
        final float calculations = calculationsService.calculate(user.getFollowers(), user.getPublicRepos());
        return new UserWsm(user.getId(), user.getLogin(), user.getName(), user.getType(), user.getAvatarUrl(), user.getCreatedAt(), calculations);
    }
}
