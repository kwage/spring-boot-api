package com.disney.studios.models.data;

import com.disney.studios.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {
    Optional<User> findById(Integer id);
}