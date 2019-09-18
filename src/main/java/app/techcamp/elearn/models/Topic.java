package app.techcamp.elearn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "course_topics")
public class Topic extends AuditModel {
    @Id
    @GeneratedValue(generator = "topic_generator")
    @SequenceGenerator(
            name = "topic_generator",
            sequenceName = "topic_sequence",
            initialValue = 1000
    )
    private Long id;
    
    @NotBlank
    @Size(min = 3, max = 100)
    private String topic_title;


    @Column(columnDefinition = "text")
    private String topic_description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Section section;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTopic_title() {
		return topic_title;
	}

	public void setTopic_title(String topic_title) {
		this.topic_title = topic_title;
	}

	public String getTopic_description() {
		return topic_description;
	}

	public void setTopic_description(String topic_description) {
		this.topic_description = topic_description;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}
    
    

    
}
