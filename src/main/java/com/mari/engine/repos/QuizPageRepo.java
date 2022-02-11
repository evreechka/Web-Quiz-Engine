package com.mari.engine.repos;

import com.mari.engine.entities.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizPageRepo extends PagingAndSortingRepository<Quiz, Long> {
    @Override
    Page<Quiz> findAll(Pageable pageable);

    @Override
    Iterable<Quiz> findAll(Sort sort);
}
