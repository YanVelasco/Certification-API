package br.com.yanvelasco.certification.models.student.controller;

import br.com.yanvelasco.certification.models.student.dto.VerifyHasCertificationDTO;
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

    public StudentController(VerifyHasCertificationUseCase verifyHasCertificationUseCase) {
        this.verifyHasCertificationUseCase = verifyHasCertificationUseCase;
    }

    @PostMapping("/verifyHasCertification")
    public Boolean verifyHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
        return verifyHasCertificationUseCase.execute(verifyHasCertificationDTO);
    }
}
