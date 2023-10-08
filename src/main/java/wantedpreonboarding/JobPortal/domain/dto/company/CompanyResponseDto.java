package wantedpreonboarding.JobPortal.domain.dto.company;

import lombok.Builder;
import wantedpreonboarding.JobPortal.domain.entity.Company;

@Builder
public class CompanyResponseDto {

    private Integer id;

    private String companyName;

    public static CompanyResponseDto of(Company company) {
        return CompanyResponseDto.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .build();
    }

}
