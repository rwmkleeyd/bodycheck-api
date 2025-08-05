package com.eyebody.bodycheck_api.community.adapter.out.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.eyebody.bodycheck_api.community.application.port.out.PostRepository;
import com.eyebody.bodycheck_api.community.domain.model.Post;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post>,
	PostRepository {

}