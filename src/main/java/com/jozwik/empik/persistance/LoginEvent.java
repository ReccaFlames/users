package com.jozwik.empik.persistance;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
public class LoginEvent {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "login_event_id", updatable = false, nullable = false)
    private UUID loginId;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Version
    @Column(name = "request_count", nullable = false)
    private Long requestCount = 0L;

    public UUID getLoginId() {
        return loginId;
    }

    public void setLoginId(UUID loginId) {
        this.loginId = loginId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Long requestCount) {
        this.requestCount = requestCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginEvent)) return false;
        LoginEvent that = (LoginEvent) o;
        return Objects.equals(loginId, that.loginId) &&
                Objects.equals(login, that.login) &&
                Objects.equals(requestCount, that.requestCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginId, login, requestCount);
    }

    @Override
    public String toString() {
        return "LoginEvent{" +
                "loginId=" + loginId +
                ", login='" + login + '\'' +
                ", requestCount=" + requestCount +
                '}';
    }
}
