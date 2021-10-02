package com.jozwik.empik.persistance;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoginEventRepository extends CrudRepository<LoginEvent, UUID> {
    Optional<LoginEvent> findByLogin(String login);
}
