package com.example.jolvre.post.dto;

import com.example.jolvre.post.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class commentResponse {
    private Long commentId;
    private String content;
    private String userName;
    private LocalDateTime createdDate;

    public static commentResponse findFromComment(Comment comment) {
        return new commentResponse(
                comment.getCommentId(),
                comment.getContent(),
                comment.getUser().getNickname(),
                comment.getCreatedDate()
        );
    }
}
