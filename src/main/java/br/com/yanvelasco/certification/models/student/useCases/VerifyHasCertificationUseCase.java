package br.com.yanvelasco.certification.models.student.useCases;

import br.com.yanvelasco.certification.models.student.dto.VerifyHasCertificationDTO;
import br.com.yanvelasco.certification.models.student.repository.CertificationStudentRepository;
import org.springframework.stereotype.Service;

@Service
public class VerifyHasCertificationUseCase {

    private final CertificationStudentRepository certificationStudentRepository;

    public VerifyHasCertificationUseCase(CertificationStudentRepository certificationStudentRepository) {
        this.certificationStudentRepository = certificationStudentRepository;
    }

    public boolean execute(VerifyHasCertificationDTO verifyHasCertificationDTO) {
        var result = certificationStudentRepository.findByStudentEmailAndTechnology (verifyHasCertificationDTO.email(), verifyHasCertificationDTO.technology());

        if(!result.isEmpty()){
            return true;
        }
        return false;
    }
}
