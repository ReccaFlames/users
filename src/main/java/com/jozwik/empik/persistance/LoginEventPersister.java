package com.jozwik.empik.persistance;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LoginEventPersister {

    private final LoginEventRepository loginEventRepository;

    public LoginEventPersister(LoginEventRepository loginEventRepository) {
        this.loginEventRepository = loginEventRepository;
    }

    @Transactional
    public void persist(String login) {
        final LoginEvent loginEvent = loginEventRepository.findByLogin(login)
                .orElseGet(LoginEvent::new);
        loginEvent.setLogin(login);
        loginEvent.setRequestCount(loginEvent.getRequestCount() + 1);
        loginEventRepository.save(loginEvent);
    }
}
