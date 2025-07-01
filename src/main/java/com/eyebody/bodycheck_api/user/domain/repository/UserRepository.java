package com.eyebody.bodycheck_api.user.domain.repository;

import java.util.List;
import java.util.Optional;

import com.eyebody.bodycheck_api.user.domain.model.User;

public interface UserRepository {

	List<User> findAll();

	Optional<User> findById(Long id);

	User save(User user);

	void deleteById(Long id);
}
