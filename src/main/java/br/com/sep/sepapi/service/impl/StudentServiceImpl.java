package br.com.sep.sepapi.service.impl;

import br.com.sep.sepapi.domain.model.Student;
import br.com.sep.sepapi.domain.repository.StudentRepository;
import br.com.sep.sepapi.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        validateStudentDoesNotExist(student);
        return studentRepository.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        Student existingStudent = getStudentOrThrow(student.getId());

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPhone(student.getPhone());

        studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long studentId) {
        if(!studentRepository.existsById(studentId))
            throw new EntityNotFoundException("Student not found with id: " + studentId);
        studentRepository.deleteById(studentId);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return getStudentOrThrow(studentId);
    }

    @Override
    public List<Student> findAllStudentsByUserId(Long userId) {
        return studentRepository.findByUserId(userId);
    }

    private void validateStudentDoesNotExist(Student student) {
        if (student.getId() != null && studentRepository.existsById(student.getId()))
            throw new IllegalArgumentException("Student already exists with id: " + student.getId());
    }

    private Student getStudentOrThrow(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }
}
