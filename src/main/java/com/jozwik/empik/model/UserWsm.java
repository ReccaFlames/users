package com.jozwik.empik.model;

import java.net.URL;
import java.time.Instant;
import java.util.Objects;

public class UserWsm {

    private final Long id;
    private final String login;
    private final String name;
    private final String type;
    private final URL avatarUrl;
    private final Instant createdAt;
    private final Float calculations;

    public UserWsm(Long id, String login, String name, String type, URL avatarUrl, Instant createdAt, Float calculations) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.calculations = calculations;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public URL getAvatarUrl() {
        return avatarUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Float getCalculations() {
        return calculations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserWsm)) return false;
        UserWsm userWsm = (UserWsm) o;
        return Objects.equals(id, userWsm.id) &&
                Objects.equals(login, userWsm.login) &&
                Objects.equals(name, userWsm.name) &&
                Objects.equals(type, userWsm.type) &&
                Objects.equals(avatarUrl, userWsm.avatarUrl) &&
                Objects.equals(createdAt, userWsm.createdAt) &&
                Objects.equals(calculations, userWsm.calculations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, name, type, avatarUrl, createdAt, calculations);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", avatarUrl=" + avatarUrl +
                ", createdAt=" + createdAt +
                ", calculations=" + calculations +
                '}';
    }
}
