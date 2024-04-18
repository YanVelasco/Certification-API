package br.com.yanvelasco.certification.models.student.controller;

import br.com.yanvelasco.certification.models.student.dto.StudentCertificationAnswersDTO;
import br.com.yanvelasco.certification.models.student.dto.VerifyHasCertificationDTO;
import br.com.yanvelasco.certification.models.student.entity.CertificationStudentEntity;
import br.com.yanvelasco.certification.models.student.useCases.StudantCertificationAnswersUseCase;
import br.com.yanvelasco.certification.models.student.useCases.VerifyHasCertificationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final VerifyHasCertificationUseCase verifyHasCertificationUseCase;

    private final StudantCertificationAnswersUseCase studantCertificationAnswersUseCase;

    public StudentController(VerifyHasCertificationUseCase verifyHasCertificationUseCase, StudantCertificationAnswersUseCase studantCertificationAnswersUseCase) {
        this.verifyHasCertificationUseCase = verifyHasCertificationUseCase;
        this.studantCertificationAnswersUseCase = studantCertificationAnswersUseCase;
    }

    @PostMapping("/verifyHasCertification")
    public Boolean verifyHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
        return verifyHasCertificationUseCase.execute (verifyHasCertificationDTO);
    }

    @PostMapping("/certification")
    public ResponseEntity<Object> certification(@RequestBody StudentCertificationAnswersDTO studentCertificationAnswersDTO) {
        try {
            return ResponseEntity.ok (studantCertificationAnswersUseCase.execute (studentCertificationAnswersDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status (400).body (e.getMessage ());
        }
    }
}
