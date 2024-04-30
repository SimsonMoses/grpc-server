package com.learn.entity;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CourseContent {
    @Id
    private ObjectId _id;
    private String uniqueId; // content id
    private String courseId;
    private String contentDescription;
    private String contentType;  // video image audio document
}
