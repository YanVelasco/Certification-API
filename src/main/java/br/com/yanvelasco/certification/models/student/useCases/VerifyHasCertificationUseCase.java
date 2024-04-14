package br.com.yanvelasco.certification.models.student.useCases;

import br.com.yanvelasco.certification.models.student.dto.VerifyHasCertificationDTO;
import org.springframework.stereotype.Service;

@Service
public class VerifyHasCertificationUseCase {
    public boolean execute(VerifyHasCertificationDTO verifyHasCertificationDTO) {
        if(verifyHasCertificationDTO.email().equalsIgnoreCase ("yancarlosduartevelasco@gmail.com") && verifyHasCertificationDTO.technology().equalsIgnoreCase ("Java")) {
            return true;
        }
        return false;
    }
}
