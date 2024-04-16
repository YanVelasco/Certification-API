package br.com.yanvelasco.certification.models.questions.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;
@Builder
public record QuestionResponseDTO(
        UUID id,
        String description,
        String technology,
        List<AlternativesResponseDTO> alternatives
) {
}
