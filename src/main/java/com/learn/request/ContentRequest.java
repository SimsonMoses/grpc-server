package com.learn.request;

import lombok.Data;

@Data
public class ContentRequest {
    private String courseId;
    private String contentId;
    private String contentDescription;
    private String contentType;
}
