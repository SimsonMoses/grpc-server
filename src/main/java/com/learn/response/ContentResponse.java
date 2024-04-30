package com.learn.response;

import lombok.Data;

@Data
public class ContentResponse {
    private String contentId; // content id
    private String contentDescription;
    private String courseId;
    private String contentType;
}
