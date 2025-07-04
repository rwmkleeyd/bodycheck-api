package com.eyebody.bodycheck_api.community.infra.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.eyebody.bodycheck_api.user.application.in.UserRepository;
import com.eyebody.bodycheck_api.user.domain.model.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>,
	UserRepository {
	Optional<User> findByEmail(String email);
}