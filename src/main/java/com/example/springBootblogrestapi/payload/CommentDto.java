package com.example.springBootblogrestapi.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;

    @NotEmpty(message = "name should not be null or empty")
    private String name;

    @NotEmpty(message = "email should not be null or empty")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10 , message = "body should  be minimum 10 characters")
    private String body;
}
