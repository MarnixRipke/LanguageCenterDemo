package nl.miwnn.ch16.marnix.languagecenterdemo.repositories;


import nl.miwnn.ch16.marnix.languagecenterdemo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
