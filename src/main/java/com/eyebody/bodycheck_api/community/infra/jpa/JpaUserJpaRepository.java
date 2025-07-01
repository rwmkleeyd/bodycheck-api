package com.eyebody.bodycheck_api.community.infra.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.eyebody.bodycheck_api.user.domain.model.User;
import com.eyebody.bodycheck_api.user.domain.repository.UserRepository;

@Repository
public interface JpaUserJpaRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>,
	UserRepository {
	Optional<User> findByEmail(String email);
}