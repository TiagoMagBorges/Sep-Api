package br.com.sep.sepapi.service;

import br.com.sep.sepapi.domain.model.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Long studentId);

    Student getStudentById(Long studentId);

    List<Student> findAllStudentsByUserId(Long userId);
}
