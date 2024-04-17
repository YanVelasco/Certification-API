package br.com.yanvelasco.certification.models.student.repository;

import br.com.yanvelasco.certification.models.student.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    public Optional<StudentEntity> findByEmail(String email);
}
