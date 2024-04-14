package br.com.yanvelasco.certification.models.student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswersCertificationsEntity {
    private UUID id;
    private UUID certificationId;
   private UUID studentId;
   private UUID answerId;
   private Boolean isCorrect;
}
