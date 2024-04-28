package com.example.jolvre.post.api;

import com.example.jolvre.post.entity.Comment;
import com.example.jolvre.post.entity.Post;
import com.example.jolvre.post.service.CommentService;
import com.example.jolvre.post.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Post", description = "커뮤니티 API")
@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/api/post/upload")
    private Post uploadPost(Post post) {
        return postService.upload(post);
    }

    @PostMapping("/api/post/update")
    private Post updatePost(Post post) {
        return postService.Update(post);
    }

    @PostMapping("/api/post/delete")
    private void deletePost(Post post) {
        postService.delete(post);
    }

    @PostMapping("/api/comment/upload")
    private Comment uploadComment(Comment comment) {
        return commentService.upload(comment);
    }

    @PostMapping("/api/comment/update")
    private Comment updateComment(Comment comment) {
        return commentService.update(comment);
    }

    @PostMapping("/api/comment/delete")
    private void deleteComment(Comment comment) {
        commentService.delete(comment);
    }
}