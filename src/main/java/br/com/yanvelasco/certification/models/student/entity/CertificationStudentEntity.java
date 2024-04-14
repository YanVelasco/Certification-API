package br.com.yanvelasco.certification.models.student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationStudentEntity {

    private UUID id;
    private UUID studentId;
    private String technology;
    private Integer grade;
    private List <AnswersCertificationsEntity> answersCertificationsEntity;

}
