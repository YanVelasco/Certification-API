package br.com.yanvelasco.certification.models.student.useCases;

import br.com.yanvelasco.certification.models.questions.entity.AlternativesEntity;
import br.com.yanvelasco.certification.models.questions.repository.QuestionRepository;
import br.com.yanvelasco.certification.models.student.dto.StudentCertificationAnswersDTO;
import org.springframework.stereotype.Service;

@Service
public class StudantCertificationAnswersUseCase {

    private final QuestionRepository questionRepository;

    public StudantCertificationAnswersUseCase(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public StudentCertificationAnswersDTO execute(StudentCertificationAnswersDTO studentCertificationAnswersDTO) {

        var questions = questionRepository.findByTechnology(studentCertificationAnswersDTO.technology());

        studentCertificationAnswersDTO.answers().stream().forEach(answers -> {

            var questionEntity = questions.stream().filter(question -> question.getId().equals(answers.getQuestionId()));

            var findCorrectAlternatives = questionEntity.findFirst ().get ().getAlternatives().stream().filter(AlternativesEntity::isCorrect).findFirst().orElseThrow(
                    () -> new RuntimeException("Correct alternative not found")
            );

            if (findCorrectAlternatives.getId().equals(answers.getAlternativeId())) {
                answers.setIsCorrect(true);
            } else {
                answers.setIsCorrect(false);
            }
        });

        return studentCertificationAnswersDTO;
    }
}