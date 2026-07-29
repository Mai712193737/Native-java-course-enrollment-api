package com.coursemanagement.repository;
import com.coursemanagement.model.Student;
import java.util.Optional;
import java.util.List;
public interface CourseRepository {
    /*save
     findById
     findAll
     deleteById
     existsById*/

    void save(Student student);

    Optional<Student> findById(int id);

    List<Student> findAll();

    void deleteById(int id);

    boolean existsById(int id);
}
