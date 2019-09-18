package app.techcamp.elearn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.techcamp.elearn.exceptions.ResourceNotFoundException;
import app.techcamp.elearn.models.Section;
import app.techcamp.elearn.repositories.CourseRepository;
import app.techcamp.elearn.repositories.SectionRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SectionController {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses/{courseId}/sections")
    public List<Section> getSectionsBycourseId(@PathVariable Long courseId) {
    	 if(!courseRepository.existsById(courseId)) {
             throw new ResourceNotFoundException("course not found with id " + courseId);
         }
        return sectionRepository.findByCourseId(courseId);
    }

    @PostMapping("/courses/{courseId}/sections")
    public Section addSection(@PathVariable Long courseId,
                            @Valid @RequestBody Section section) {
        return courseRepository.findById(courseId)
                .map(course -> {
                	section.setCourse(course);
                    return sectionRepository.save(section);
                }).orElseThrow(() -> new ResourceNotFoundException("course not found with id " + courseId));
    }

    @PutMapping("/courses/{courseId}/sections/{sectionId}")
    public Section updateSection(@PathVariable Long courseId,
                               @PathVariable Long sectionId,
                               @Valid @RequestBody Section sectionRequest) {
        if(!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("course not found with id " + courseId);
        }

        return sectionRepository.findById(sectionId)
                .map(section -> {
                    section.setSection_title(sectionRequest.getSection_title());
                    section.setSection_description(sectionRequest.getSection_description());
                    return sectionRepository.save(section);
                }).orElseThrow(() -> new ResourceNotFoundException("Section not found with id " + sectionId));
    }

    @DeleteMapping("/courses/{courseId}/sections/{sectionId}")
    public ResponseEntity<?> deleteSection(@PathVariable Long courseId,
                                          @PathVariable Long sectionId) {
        if(!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("course not found with id " + courseId);
        }

        return sectionRepository.findById(sectionId)
                .map(section -> {
                    sectionRepository.delete(section);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Section not found with id " + sectionId));

    }
}
