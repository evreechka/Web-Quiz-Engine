package com.mari.engine.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
@Entity
@JsonIgnoreProperties(value = {"answer", "author"}, allowSetters = true)
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Title cannot be blank!")
    private String title;
    @NotBlank(message = "text cannot be blank!")
    private String text;
    @Size(min = 2, message = "Options should contains at least 2 values!")
    @NotNull(message = "Options should not be null!")
    @ElementCollection
    private List<String> options;
    @ElementCollection
    @JsonProperty(value = "answer")
    private List<Integer> answer = new ArrayList<>();
    @JsonProperty(value = "author")
    private String author;
    public Quiz() {

    }

    public Quiz(String title, String text, List<String> options, List<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
