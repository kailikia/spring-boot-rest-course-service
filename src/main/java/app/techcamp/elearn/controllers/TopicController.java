package app.techcamp.elearn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.techcamp.elearn.exceptions.ResourceNotFoundException;
import app.techcamp.elearn.models.Section;
import app.techcamp.elearn.models.Topic;
import app.techcamp.elearn.repositories.SectionRepository;
import app.techcamp.elearn.repositories.TopicRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @GetMapping("/sections/{sectionId}/topics")
    public List<Topic> getSectionsBycourseId(@PathVariable Long sectionId) {
    	
    		return sectionRepository.findById(sectionId)
    				.map(section -> {
    					return topicRepository.findBySectionId(sectionId);
    				}).orElseThrow(() -> new ResourceNotFoundException("section not found with id " + sectionId));
    	}

    @PostMapping("/sections/{sectionId}/topics")
    public Topic addTopic(@PathVariable Long sectionId,
                            @Valid @RequestBody Topic topic) {
    	
        return sectionRepository.findById(sectionId)
                .map(section -> {
                	topic.setSection(section);
                    return topicRepository.save(topic);
                }).orElseThrow(() -> new ResourceNotFoundException("section not found with id " + sectionId));
    }

    @PutMapping("/sections/{sectionId}/topic/{topicId}")
    public Topic updateSection(@PathVariable Long sectionId,
                               @PathVariable Long topicId,
                               @Valid @RequestBody Topic topicRequest) {
        if(!topicRepository.existsById(topicId) || !sectionRepository.existsById(sectionId)) {
            throw new ResourceNotFoundException("topic or section not found with section id " + sectionId + " or topic id " + topicId);
        }

        return topicRepository.findById(topicId)
                .map(topic -> {
                    topic.setTopic_description(topicRequest.getTopic_description());
                    topic.setTopic_title(topicRequest.getTopic_title());
                    return topicRepository.save(topic);
                }).orElseThrow(() -> new ResourceNotFoundException("topic not found with id " + topicId));
    }

    @DeleteMapping("/sections/{sectionId}/topic/{topicId}")
    public ResponseEntity<?> deleteSection(@PathVariable Long sectionId,
                                          @PathVariable Long topicId) {
        if(!topicRepository.existsById(topicId) || !sectionRepository.existsById(sectionId)) {
        	 throw new ResourceNotFoundException("topic or section not found with section id " + sectionId + " or topic id " + topicId);
        }

        return topicRepository.findById(topicId)
                .map(topic -> {
                    topicRepository.delete(topic);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Topic not found with id " + topicId));

    }
}

