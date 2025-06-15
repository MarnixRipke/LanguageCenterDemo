package nl.miwnn.ch16.marnix.languagecenterdemo.repositories;


import nl.miwnn.ch16.marnix.languagecenterdemo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByName(String name);
}
