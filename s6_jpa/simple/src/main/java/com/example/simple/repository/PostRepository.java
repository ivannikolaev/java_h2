package com.example.simple.repository;

import com.example.simple.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"images"})
    Optional<Post> getPostById(Long id);


    @EntityGraph(attributePaths = {"comments"})
    Optional<Post> queryPostById(Long id);


    @EntityGraph(attributePaths = {"images", "comments"})
    Optional<Post> readPostById(Long id);

}
