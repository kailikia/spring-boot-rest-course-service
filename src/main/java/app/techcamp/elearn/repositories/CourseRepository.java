package app.techcamp.elearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.techcamp.elearn.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
