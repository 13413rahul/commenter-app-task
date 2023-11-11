package com.comment.app.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CommentRequest {
    @NotNull(message = "Comment from user name cannot be null")
    @NotBlank(message = "Comment from user name cannot be blank")
    @NotEmpty(message = "Comment from user name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z\\\\s-]+$")
    private String commentTo;

    @NotNull(message = "Comment from user name cannot be null")
    @NotBlank(message = "Comment from user name cannot be blank")
    @NotEmpty(message = "Comment from user name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z\\\\s-]+$")
    private String commentFrom;

    @NotNull(message = "Comment from user name cannot be null")
    @NotBlank(message = "Comment from user name cannot be blank")
    @NotEmpty(message = "Comment from user name cannot be empty")
    private String comment;
}
