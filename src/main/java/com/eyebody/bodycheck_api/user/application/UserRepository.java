package com.eyebody.bodycheck_api.user.application;

import java.util.List;
import java.util.Optional;

import com.eyebody.bodycheck_api.user.domain.model.User;

public interface UserRepository {

	Optional<User> findById(Long id);

	Optional<User> findByEmail(String email);

	List<User> findAll();

	User save(User user);

	void delete(User user);

	boolean existsByEmail(String email);
}
