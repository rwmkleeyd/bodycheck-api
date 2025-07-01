package com.eyebody.bodycheck_api.community.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.eyebody.bodycheck_api.community.domain.model.Post;
import com.eyebody.bodycheck_api.community.domain.repository.PostRepository;

@Repository
public interface JpaPostJpaRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post>,
	PostRepository {

}