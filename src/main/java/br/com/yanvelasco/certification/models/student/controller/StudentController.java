package br.com.yanvelasco.certification.models.student.controller;

import br.com.yanvelasco.certification.models.student.dto.StudentCertificationAnswersDTO;
import br.com.yanvelasco.certification.models.student.dto.VerifyHasCertificationDTO;
import br.com.yanvelasco.certification.models.student.useCases.StudantCertificationAnswersUseCase;
import br.com.yanvelasco.certification.models.student.useCases.VerifyHasCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public StudentCertificationAnswersDTO certification(@RequestBody StudentCertificationAnswersDTO studentCertificationAnswersDTO) {
        return studantCertificationAnswersUseCase.execute(studentCertificationAnswersDTO);
    }
}
