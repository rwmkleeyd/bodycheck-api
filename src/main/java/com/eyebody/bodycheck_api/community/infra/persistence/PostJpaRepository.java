package com.eyebody.bodycheck_api.community.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.eyebody.bodycheck_api.community.domain.model.Post;

public interface PostJpaRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
}