package app.techcamp.elearn.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "sections")
public class Section extends AuditModel {
    @Id
    @GeneratedValue(generator = "section_generator")
    @SequenceGenerator(
            name = "section_generator",
            sequenceName = "section_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(columnDefinition = "text")
    private String section_description;
    
    @NotBlank
    @Size(min = 3, max = 100)
    private String section_title;

    public String getSection_title() {
		return section_title;
	}

	public void setSection_title(String section_title) {
		this.section_title = section_title;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Course course;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSection_description() {
		return section_description;
	}

	public void setSection_description(String section_description) {
		this.section_description = section_description;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
        
}
