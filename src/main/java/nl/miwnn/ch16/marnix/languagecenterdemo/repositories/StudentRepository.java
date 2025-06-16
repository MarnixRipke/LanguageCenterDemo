package nl.miwnn.ch16.marnix.languagecenterdemo.repositories;


import nl.miwnn.ch16.marnix.languagecenterdemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
