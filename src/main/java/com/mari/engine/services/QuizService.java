package com.mari.engine.services;

import com.mari.engine.entities.Quiz;
import com.mari.engine.entities.Response;
import com.mari.engine.repos.QuizPageRepo;
import com.mari.engine.repos.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private QuizPageRepo quizPageRepo;
    @Autowired
    private CompletedQuizService completedQuizService;
    public Quiz getQuizById(long id) {
        Quiz quiz = quizRepo.findQuizById(id);
        if (quiz == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz with id=" + id + " doesn't exist!");
        return quiz;
    }
    public Page<Quiz> getAllQuizzes(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return quizPageRepo.findAll(paging);
    }
    public Response solveQuiz(long id, List<Integer> answer, String username) {
        Quiz quiz = getQuizById(id);
        if (quiz == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz with id=" + id + " doesn't exist!");
//        System.out.println(quiz.getAnswer());
//        System.out.println(answer);
        if (quiz.getAnswer().containsAll(answer) && answer.containsAll(quiz.getAnswer())) {
            completedQuizService.completeQuiz(id, username);
            return new Response(true, "Congratulations, you're right!");
        }
        return new Response(false, "Wrong answer! Please, try again.");
    }

    public Quiz addQuiz(Quiz newQuiz, String username) {
        newQuiz.setAuthor(username);
        quizRepo.save(newQuiz);
        return newQuiz;
    }

    public void deleteQuiz(long id, String username) {
        Quiz quiz = quizRepo.findQuizById(id);
        if (quiz == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz with id=" + id + " doesn't exist!");
        if (!quiz.getAuthor().equals(username))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You haven't permission to delete quiz with id=" + id);
        quizRepo.deleteById(id);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Quiz was successfully deleted!");
    }

    public void updateQuiz(long id, Quiz newQuiz, String username) {
        Quiz oldQuiz = quizRepo.findQuizById(id);
        if (oldQuiz == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz with id=" + id + " doesn't exist!");
        if (!oldQuiz.getAuthor().equals(username))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You haven't permission to delete quiz with id=" + id);
        oldQuiz.setTitle(newQuiz.getTitle());
        oldQuiz.setText(newQuiz.getText());
        oldQuiz.setOptions(newQuiz.getOptions());
        oldQuiz.setAnswer(newQuiz.getAnswer());
        quizRepo.save(oldQuiz);
    }
}
