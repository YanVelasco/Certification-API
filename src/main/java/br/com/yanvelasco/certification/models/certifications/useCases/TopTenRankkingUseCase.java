package br.com.yanvelasco.certification.models.certifications.useCases;

import br.com.yanvelasco.certification.models.student.entity.CertificationStudentEntity;
import br.com.yanvelasco.certification.models.student.repository.CertificationStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopTenRankkingUseCase {
    private final CertificationStudentRepository certificationStudentRepository;

    public TopTenRankkingUseCase(CertificationStudentRepository certificationStudentRepository) {
        this.certificationStudentRepository = certificationStudentRepository;
    }

    public List<CertificationStudentEntity> topTen() {
        return certificationStudentRepository.findTop10ByOrderByGradeDesc();
    }

}
