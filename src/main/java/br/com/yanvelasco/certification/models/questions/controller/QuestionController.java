package br.com.yanvelasco.certification.models.questions.controller;

import br.com.yanvelasco.certification.models.questions.dto.AlternativesResponseDTO;
import br.com.yanvelasco.certification.models.questions.dto.QuestionResponseDTO;
import br.com.yanvelasco.certification.models.questions.entity.AlternativesEntity;
import br.com.yanvelasco.certification.models.questions.entity.QuestionEntity;
import br.com.yanvelasco.certification.models.questions.repository.QuestionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionRepository questionRepository; // Made final

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/technology/{technology}")
    public List<QuestionResponseDTO> finByTechnology(@PathVariable String technology) {
        var result = questionRepository.findByTechnology(technology);



        return result.stream()
                .map(questionDTO -> questionResponseDTO(questionDTO, questionDTO.getAlternatives().get(0)))
                .collect(Collectors.toList());
    }

    // Assuming future use within the class, hence made private and non-static
    private QuestionResponseDTO questionResponseDTO(QuestionEntity questionDTO, AlternativesEntity alternativesDTO) {

        List<AlternativesResponseDTO> alternativesResponseDTOs = questionDTO.getAlternatives().stream()
                .map(alternativesEntity -> AlternativesResponseDTO.builder()
                        .id(alternativesEntity.getId())
                        .description(alternativesEntity.getDescription())
                        .build())
                .collect(Collectors.toList());

        return QuestionResponseDTO.builder()
                .id(questionDTO.getId())
                .description(questionDTO.getDescription())
                .technology(questionDTO.getTechnology())
                .alternatives(alternativesResponseDTOs) // Aqui você passa a lista completa de alternativas
                .build();
    }
}