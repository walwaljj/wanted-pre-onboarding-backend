package wantedpreonboarding.JobPortal.domain.dto.company;

import lombok.Builder;
import wantedpreonboarding.JobPortal.domain.entity.Company;

@Builder
public class CompanyRequestDto {

    private Integer id;

    private String companyName;

    public static CompanyRequestDto of(Company company){
        return CompanyRequestDto.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .build();

    }
}
