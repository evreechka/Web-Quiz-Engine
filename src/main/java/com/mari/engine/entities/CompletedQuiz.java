package com.mari.engine.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
@JsonIgnoreProperties(value = {"completedId", "user"}, allowSetters = true)
@Entity
public class CompletedQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("completedId")
    private long id;
    private LocalDateTime completedAt;
    @JsonProperty("id")
    private long quizId;
    private String user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
