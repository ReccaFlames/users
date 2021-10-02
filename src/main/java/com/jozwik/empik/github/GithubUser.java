package com.jozwik.empik.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;
import java.time.Instant;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUser {

    private final Long id;
    private final String login;
    private final String name;
    private final URL avatarUrl;
    private final String type;
    private final int followers;
    private final int publicRepos;
    private final Instant createdAt;

    public GithubUser(@JsonProperty("id") Long id,
                      @JsonProperty("login") String login,
                      @JsonProperty("name") String name,
                      @JsonProperty("avatar_url") URL avatarUrl,
                      @JsonProperty("type") String type,
                      @JsonProperty("followers") int followers,
                      @JsonProperty("public_repos") int publicRepos,
                      @JsonProperty("created_at") Instant createdAt
    ) {
        this.id = Objects.requireNonNull(id);
        this.login = login;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.type = type;
        this.followers = followers;
        this.publicRepos = publicRepos;
        this.createdAt = createdAt;
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

    public URL getAvatarUrl() {
        return avatarUrl;
    }

    public String getType() {
        return type;
    }

    public int getFollowers() {
        return followers;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GithubUser)) return false;
        GithubUser that = (GithubUser) o;
        return followers == that.followers &&
                publicRepos == that.publicRepos &&
                Objects.equals(id, that.id) &&
                Objects.equals(login, that.login) &&
                Objects.equals(name, that.name) &&
                Objects.equals(avatarUrl, that.avatarUrl) &&
                Objects.equals(type, that.type) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, name, avatarUrl, type, followers, publicRepos, createdAt);
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", avatarUrl=" + avatarUrl +
                ", type='" + type + '\'' +
                ", followers=" + followers +
                ", publicRepos=" + publicRepos +
                ", createdAt=" + createdAt +
                '}';
    }
}
