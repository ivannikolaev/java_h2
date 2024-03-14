package com.example.simple;

public record PostCommentDto(
        Long commentId,
        Long postId,
        String postTitle,
        String commentText
) {
}
