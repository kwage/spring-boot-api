package com.disney.studios.models.data;

import com.disney.studios.models.Dog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface DogDao extends CrudRepository<Dog, Integer> {
    Optional<Dog> findById(Integer id);
}