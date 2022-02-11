package com.mari.engine.repos;

import com.mari.engine.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public
interface QuizRepo extends JpaRepository<Quiz, Long> {
    List<Quiz> findAll();
    Quiz findQuizById(Long id);
    @Transactional
    void deleteById(Long id);
}
