package nl.miwnn.ch16.marnix.languagecenterdemo.repositories;

import nl.miwnn.ch16.marnix.languagecenterdemo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository <Course, Long> {
}
