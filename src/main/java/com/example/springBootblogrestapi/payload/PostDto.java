package com.example.springBootblogrestapi.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private long id;

    //title should not be null or empty
    // title should have atleast 2 characters

    @NotEmpty
    @Size(min = 2 , message = "post title should have atleast 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 10 , message = "post description should have atleast 10 characters")
    private String description;

    @NotEmpty
    private String content;
    private Set<CommentDto> comments;


    private Long categoryId;

//        public void getComments() {
//        this.comments = comments;
//    }
}
