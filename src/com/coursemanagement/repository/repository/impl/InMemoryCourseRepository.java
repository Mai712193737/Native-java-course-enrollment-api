package com.coursemanagement.repository.repository.impl;

import com.coursemanagement.model.Course;
import com.coursemanagement.repository.CourseRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryCourseRepository implements CourseRepository {

    private Map<Long, Course> courses = new HashMap<>();
    private long nextId = 1L;

    @Override
    public void save(Course course) {
        if (course.getId() == null) {
            course.setId(nextId++);
        }
        courses.put(course.getId(), course);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.ofNullable(courses.get(id));
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courses.values());
    }

    @Override
    public void deleteById(Long id) {
        courses.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return courses.containsKey(id);
    }
}