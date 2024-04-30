package com.learn.repository;

import com.learn.entity.CourseContent;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseContentRepository extends MongoRepository<CourseContent, ObjectId> {
    CourseContent findByCourseIdAndUniqueId(String courseId, String contentId);
}
