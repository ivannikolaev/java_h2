package com.example.simple.repository;

import com.example.simple.entity.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentByMessageIgnoreCase(String text);

    @EntityGraph(attributePaths = "post")
    List<Comment> getCommentByMessageIgnoreCase(String text);
}
