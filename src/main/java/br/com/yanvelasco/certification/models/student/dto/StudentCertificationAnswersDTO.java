package br.com.yanvelasco.certification.models.student.dto;

import lombok.Builder;

import java.util.List;
@Builder
public record StudentCertificationAnswersDTO(
        String email,
        String technology,
        List<QuestionAnswerDTO> answers
) {
}
