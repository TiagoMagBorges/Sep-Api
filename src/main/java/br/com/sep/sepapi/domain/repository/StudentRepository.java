package br.com.sep.sepapi.domain.repository;

import br.com.sep.sepapi.domain.model.Student;
import br.com.sep.sepapi.domain.model.User; // Pode ser útil importar o User
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByUserId(Long userId);
}