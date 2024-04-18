package br.com.yanvelasco.certification.models.student.useCases;

import br.com.yanvelasco.certification.models.questions.entity.AlternativesEntity;
import br.com.yanvelasco.certification.models.questions.entity.QuestionEntity;
import br.com.yanvelasco.certification.models.questions.repository.QuestionRepository;
import br.com.yanvelasco.certification.models.student.dto.StudentCertificationAnswersDTO;
import br.com.yanvelasco.certification.models.student.dto.VerifyHasCertificationDTO;
import br.com.yanvelasco.certification.models.student.entity.AnswersCertificationsEntity;
import br.com.yanvelasco.certification.models.student.entity.CertificationStudentEntity;
import br.com.yanvelasco.certification.models.student.entity.StudentEntity;
import br.com.yanvelasco.certification.models.student.repository.CertificationStudentRepository;
import br.com.yanvelasco.certification.models.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudantCertificationAnswersUseCase {

    private final CertificationStudentRepository certificationStudentRepository;

    private final StudentRepository studentRepository;

    private final QuestionRepository questionRepository;

    private final VerifyHasCertificationUseCase verifyHasCertificationUseCase;

    private int correctAnswers = 0;

    public StudantCertificationAnswersUseCase(CertificationStudentRepository certificationStudentRepository, StudentRepository studentRepository, QuestionRepository questionRepository, VerifyHasCertificationUseCase verifyHasCertificationUseCase) {
        this.certificationStudentRepository = certificationStudentRepository;
        this.studentRepository = studentRepository;
        this.questionRepository = questionRepository;
        this.verifyHasCertificationUseCase = verifyHasCertificationUseCase;
    }

    public List<CertificationStudentEntity> execute(StudentCertificationAnswersDTO studentCertificationAnswersDTO) {

        var hasCertication = verifyHasCertificationUseCase.execute ( new VerifyHasCertificationDTO (studentCertificationAnswersDTO.email (), studentCertificationAnswersDTO.technology ()) );

        if (hasCertication) {
            throw new RuntimeException ("Student already has certification");
        }

        List<QuestionEntity> questions = questionRepository.findByTechnology (studentCertificationAnswersDTO.technology ());

        List<AnswersCertificationsEntity> answersCertificationsEntities = new ArrayList<> ();

        studentCertificationAnswersDTO.answers ().forEach (answers -> {

            var questionEntity = questions.stream ().filter (question -> question.getId ().equals (answers.getQuestionId ())).findFirst ().orElseThrow (
                    () -> new RuntimeException ("Question not found")
            );

            var findCorrectAlternatives = questionEntity.getAlternatives ().stream ().filter (AlternativesEntity::isCorrect).findFirst ().orElseThrow (
                    () -> new RuntimeException ("Correct alternative not found")
            );

            if (findCorrectAlternatives.getId ().equals (answers.getAlternativeId ())) {
                correctAnswers++;
            }

            answers.setIsCorrect (findCorrectAlternatives.getId ().equals (answers.getAlternativeId ()));

            var answerCertification = AnswersCertificationsEntity.builder ()
                    .answerId (answers.getAlternativeId ())
                    .questionId (answers.getQuestionId ())
                    .isCorrect (answers.getIsCorrect ())
                    .build ();

            answersCertificationsEntities.add (answerCertification);
        });

        var studentEntity = studentRepository.findByEmail (studentCertificationAnswersDTO.email ());
        UUID studentId;
        if (studentEntity.isEmpty ()) {
            var studentCreated = StudentEntity.builder ()
                    .email (studentCertificationAnswersDTO.email ())
                    .build ();
            studentRepository.save (studentCreated);
            studentId = studentCreated.getId ();
        } else {
            studentId = studentEntity.get ().getId ();
        }

        var certificationStudentEntity = CertificationStudentEntity.builder ()
                .technology (studentCertificationAnswersDTO.technology ())
                .studentId (studentId)
                .grade (correctAnswers)
                .build ();

        var cretificationCreated = certificationStudentRepository.save (certificationStudentEntity);

        answersCertificationsEntities.forEach (answer -> {
            answer.setCertificationId (certificationStudentEntity.getId ());
            answer.setCertificationStudentEntity (certificationStudentEntity);
        });

        certificationStudentEntity.setAnswersCertificationsEntity (answersCertificationsEntities);

        certificationStudentRepository.save (certificationStudentEntity);


        List<CertificationStudentEntity> certificationStudentEntities = new ArrayList<> ();
        certificationStudentEntities.add (cretificationCreated);
        return certificationStudentEntities;
    }
}