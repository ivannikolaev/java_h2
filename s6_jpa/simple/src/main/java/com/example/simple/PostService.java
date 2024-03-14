package com.example.simple;

import com.example.simple.entity.Comment;
import com.example.simple.entity.Post;
import com.example.simple.repository.CommentRepository;
import com.example.simple.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // late initialization section

    @Transactional
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Post getPostByIdWithEntityGraph(Long id) {
        return postRepository.getPostById(id).orElseThrow();
    }

    // N + 1 section

    @Transactional
    public List<PostCommentDto> getPostByCommentText(String text) {
        List<Comment> comments = commentRepository.findCommentByMessageIgnoreCase(text);
        return mapCommentToDto(comments);

    }

    @Transactional
    public List<PostCommentDto> getPostByCommentTextWithEntityGraph(String text) {
        List<Comment> comments = commentRepository.getCommentByMessageIgnoreCase(text);
        return mapCommentToDto(comments);

    }

    private static List<PostCommentDto> mapCommentToDto(List<Comment> comments) {
        return comments
                .stream()
                .map(comment ->
                        new PostCommentDto(
                                comment.getId(), comment.getPost().getId(),
                                comment.getPost().getTitle(), comment.getMessage())
                )
                .toList();
    }

    // Cartesian product section

    @Transactional
    public Post getPostWithAllLinkedEntities(Long id) {
        return postRepository.readPostById(id).orElseThrow();
    }

    // ToString section

    @Transactional
    public Post getPostAndLog(Long id) {
        Post post = postRepository.getPostById(id).orElseThrow();
        log.info("Log post {}", post.toString());
        return post;
    }

    // OptimisticLock Section

    @SneakyThrows
    @Transactional
    public Post updatePostNameWithTimeout(Long id, String newTitle) {
        Post byId = postRepository.findById(id).orElseThrow();
        byId.setTitle(newTitle);
        Thread.sleep(2000);
        Post afterUpdate = postRepository.save(byId);
        log.info("UPDATE WITH TIMEOUT");
        return afterUpdate;
    }

    @Transactional
    @SneakyThrows
    public Post updatePostNameWithoutTimeout(Long id, String newTitle) {
        Post byId = postRepository.findById(id).orElseThrow();
        byId.setTitle(newTitle);
        Thread.sleep(100);
        Post afterUpdate = postRepository.save(byId);
        log.info("UPDATE WITHOUT TIMEOUT");
        return afterUpdate;
    }

    // Bidirectional relationship section
    @Transactional
    public Post saveWithRelation(Long id) {
        Post byId = postRepository.findById(id).orElseThrow();
        Comment comment = new Comment();
        comment.setMessage("Bellissimo!");
        byId.getComments().add(comment);
        return postRepository.save(byId);
    }

    @Transactional
    public Post saveWithRelationOtherSide(Long id) {
        Post byId = postRepository.findById(id).orElseThrow();
        Comment comment = new Comment();
        comment.setMessage("Bellissimo!");
        comment.setPost(byId);
        return postRepository.save(byId);
    }

    @Transactional
    public Post saveWithRelationIncorrectCollectionAllocation(Long id) {
        Post byId = postRepository.findById(id).orElseThrow();
        Comment comment = new Comment();
        comment.setMessage("Bellissimo!");
        comment.setPost(byId);

        List<Comment> arrayList = new ArrayList<>(List.of(comment));
        byId.setComments(arrayList);
        return postRepository.save(byId);
    }

    @Transactional
    public Post saveWithRelationCorrectCollectionAllocation(Long id) {
        Post byId = postRepository.findById(id).orElseThrow();
        Comment comment = new Comment();
        comment.setMessage("Bellissimo!");
        comment.setPost(byId);

        byId.getComments().add(comment);
        return postRepository.save(byId);
    }


    // util
    @Transactional
    public Post getPostByIdWithEntityGraphComments(Long id) {
        return postRepository.queryPostById(id).orElseThrow();
    }


}
