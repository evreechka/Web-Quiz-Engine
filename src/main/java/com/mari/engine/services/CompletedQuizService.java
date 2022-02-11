package com.mari.engine.services;

import com.mari.engine.entities.CompletedQuiz;
import com.mari.engine.repos.CompletedQuizPageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class CompletedQuizService {
    @Autowired
    private CompletedQuizPageRepo completedQuizPageRepo;

    public void completeQuiz(long id, String user) {
        CompletedQuiz completedQuiz = new CompletedQuiz();
        completedQuiz.setQuizId(id);
        completedQuiz.setUser(user);
        completedQuiz.setCompletedAt(LocalDateTime.now());
        completedQuizPageRepo.save(completedQuiz);
    }

    public Map<String, Object> getCompletedQuizzes(String username, Integer pageSize, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("completedAt").descending());
        return generateResponse(completedQuizPageRepo.findAllByUser(username, pageable));
    }

    private Map<String, Object> generateResponse(Page<CompletedQuiz> completedQuizPage) {
        Map<String, Object> response = new HashMap<>();
        response.put("totalPages", completedQuizPage.getTotalPages());
        response.put("totalElements", completedQuizPage.getTotalElements());
        response.put("last", completedQuizPage.isLast());
        response.put("first", completedQuizPage.isFirst());
        response.put("empty", completedQuizPage.isEmpty());
        response.put("content", completedQuizPage.getContent());
        return response;
    }
}
