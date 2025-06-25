package com.eyebody.bodycheck_api.community.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.eyebody.bodycheck_api.user.domain.model.User;

public interface UserJpaRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	Optional<User> findByEmail(String email);
}