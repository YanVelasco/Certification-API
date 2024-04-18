package br.com.yanvelasco.certification.models.certifications.controllers;

import br.com.yanvelasco.certification.models.certifications.useCases.TopTenRankkingUseCase;
import br.com.yanvelasco.certification.models.student.entity.CertificationStudentEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    private final TopTenRankkingUseCase topTenRankkingUseCase;

    public RankingController(TopTenRankkingUseCase topTenRankkingUseCase) {
        this.topTenRankkingUseCase = topTenRankkingUseCase;
    }

    @GetMapping("/top10")
    public List<CertificationStudentEntity> ranking() {
       return topTenRankkingUseCase.topTen ();
    }


}
