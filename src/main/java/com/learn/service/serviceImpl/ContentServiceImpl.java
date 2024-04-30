package com.learn.service.serviceImpl;

import com.learn.entity.CourseContent;
import com.learn.repository.CourseContentRepository;
import com.learn.request.ContentRequest;
import com.learn.response.CommonResponse;
import com.learn.response.ContentResponse;
import com.learn.response.ResponseStatus;
import com.learn.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private CourseContentRepository courseContentRepository;

    public CommonResponse addCourse(ContentRequest contentRequest) {
        CommonResponse response = new CommonResponse();
        CourseContent courseContent = new CourseContent();
        courseContent.setUniqueId(UUID.randomUUID().toString());
        courseContent.setCourseId(contentRequest.getCourseId());
        courseContent.setContentDescription(courseContent.getContentDescription());
        courseContent.setContentType(contentRequest.getContentType());
        courseContentRepository.save(courseContent);
        return response;
    }

    @Override
    public CommonResponse getContent(ContentRequest contentRequest) {
        CommonResponse commonResponse = new CommonResponse();
        CourseContent courseContent = courseContentRepository.findByCourseIdAndUniqueId(contentRequest.getCourseId(), contentRequest.getContentId());
        ContentResponse contentResponse = new ContentResponse();
        contentResponse.setCourseId(courseContent.getCourseId());
        contentResponse.setContentDescription(courseContent.getContentDescription());
        contentResponse.setContentType(courseContent.getContentType());
        contentResponse.setContentId(courseContent.getUniqueId());
        commonResponse.setData(contentResponse);
        commonResponse.setCode(200);
        commonResponse.setStatus(ResponseStatus.SUCCESS);
        commonResponse.setSuccessMessage("Msg retrieved successfully");
        return commonResponse;
    }
}
