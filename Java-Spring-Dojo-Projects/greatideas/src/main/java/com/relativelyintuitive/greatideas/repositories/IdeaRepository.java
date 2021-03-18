package com.relativelyintuitive.greatideas.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.relativelyintuitive.greatideas.models.Idea;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long> {
    Optional<Idea> findById(Long id);
    List<Idea> findAll();
}