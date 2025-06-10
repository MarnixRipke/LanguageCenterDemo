package nl.miwnn.ch16.marnix.languagecenterdemo.repositories;


import nl.miwnn.ch16.marnix.languagecenterdemo.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {


}
