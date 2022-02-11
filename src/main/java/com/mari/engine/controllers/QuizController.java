package com.mari.engine.controllers;

import com.mari.engine.entities.CompletedQuiz;
import com.mari.engine.entities.Quiz;
import com.mari.engine.entities.Response;
import com.mari.engine.services.CompletedQuizService;
import com.mari.engine.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QuizController {
    @Autowired
    private QuizService quizService;
    @Autowired
    private CompletedQuizService completedQuizService;
    @GetMapping(value = "/api/quizzes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Quiz getQuizById(@PathVariable long id) {
        return quizService.getQuizById(id);
    }

    @GetMapping(value = "/api/quizzes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Quiz> getAllQuizzes(@RequestParam(defaultValue = "0") Integer page,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    @RequestParam(defaultValue = "id") String sortBy) {
        return quizService.getAllQuizzes(page, pageSize, sortBy);
    }

    @GetMapping(value = "/api/quizzes/completed", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> getCompletedQuizzes(@AuthenticationPrincipal UserDetails userDetails,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @RequestParam(defaultValue = "0") Integer page) {
        return completedQuizService.getCompletedQuizzes(userDetails.getUsername(), pageSize, page);
    }

    @PostMapping(value = "/api/quizzes/{id}/solve", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response answerQuiz(@PathVariable long id, @RequestBody Map<String, List<Integer>> params, @AuthenticationPrincipal UserDetails userDetails) {
        return quizService.solveQuiz(id, params.get("answer"), userDetails.getUsername());
    }

    @PostMapping(value = "/api/quizzes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Quiz createQuiz(@Valid @RequestBody Quiz newQuiz, @AuthenticationPrincipal UserDetails userDetails) {
        return quizService.addQuiz(newQuiz, userDetails.getUsername());
    }

    @DeleteMapping(value = "/api/quizzes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteQuiz(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails) {
        quizService.deleteQuiz(id, userDetails.getUsername());
    }

    @PutMapping(value = "/api/quizzes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateQuiz(@PathVariable long id, @Valid @RequestBody Quiz newQuiz, @AuthenticationPrincipal UserDetails userDetails) {
        quizService.updateQuiz(id, newQuiz, userDetails.getUsername());
    }
}
