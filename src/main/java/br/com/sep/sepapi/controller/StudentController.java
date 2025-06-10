package br.com.sep.sepapi.controller;

import br.com.sep.sepapi.domain.model.Student;
import br.com.sep.sepapi.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
        student.setId(studentId);
        studentService.updateStudent(student);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}
