package br.com.yanvelasco.certification.models.questions.dto;



import lombok.Builder;

import java.util.UUID;
@Builder
public record AlternativesResponseDTO(
        UUID id,
        String description
) {
}
