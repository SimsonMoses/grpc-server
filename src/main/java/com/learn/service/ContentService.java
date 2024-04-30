package com.learn.service;

import com.learn.request.ContentRequest;
import com.learn.response.CommonResponse;

public interface ContentService {
    CommonResponse getContent(ContentRequest contentRequest);
}
