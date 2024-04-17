package br.com.yanvelasco.certification.models.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerDTO{
    private UUID questionId;
    private UUID alternativeId;
    private Boolean isCorrect;
}