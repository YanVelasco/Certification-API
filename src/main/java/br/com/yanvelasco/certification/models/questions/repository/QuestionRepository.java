package br.com.yanvelasco.certification.models.questions.repository;

import br.com.yanvelasco.certification.models.questions.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {
    List<QuestionEntity> findByTechnology(String technology);
}
