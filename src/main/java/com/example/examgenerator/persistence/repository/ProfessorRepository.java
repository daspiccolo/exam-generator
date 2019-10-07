package com.example.examgenerator.persistence.repository;

import com.example.examgenerator.persistence.model.Professor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Debora Piccolo.
 */
public interface ProfessorRepository extends PagingAndSortingRepository<Professor, Long> {
    Professor findByEmail(String email);


}
