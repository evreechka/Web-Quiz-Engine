package com.mari.engine.repos;

import com.mari.engine.entities.CompletedQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedQuizPageRepo extends PagingAndSortingRepository<CompletedQuiz, Long> {
    Page<CompletedQuiz> findAllByUser(String user, Pageable pageable);
}
