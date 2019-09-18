package app.techcamp.elearn.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.techcamp.elearn.exceptions.ResourceNotFoundException;
import app.techcamp.elearn.models.Course;
import app.techcamp.elearn.repositories.CourseRepository;

import javax.validation.Valid;

@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses")
    public Page<Course> getCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }


    @PostMapping("/courses")
    public Course createCourse(@Valid @RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("/courses/{Id}")
    public Course updateCourse(@PathVariable Long courseId,
                                   @Valid @RequestBody Course courseRequest) {
        return courseRepository.findById(courseId)
                .map(course -> {
                	course.setCourse_name(courseRequest.getCourse_name());
                	course.setDescription(courseRequest.getDescription());
                    return courseRepository.save(course);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + courseId));
    }


    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    courseRepository.delete(course);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + courseId));
    }
}