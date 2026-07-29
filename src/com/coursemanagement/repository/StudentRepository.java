package com.coursemanagement.repository;

import com.coursemanagement.model.Student;

import java.util.List;

public interface StudentRepository {
//save**
//findById**
//findByEmail**
//findAll 🤺
//existsByEmail **
//deleteById**
    void save(Student student );

    String findById(int id );

    String findByEmail(String Email);

    List<Student> findAll();

    Boolean existsByEmail( String Email);

    void deleteById (int id );
}
