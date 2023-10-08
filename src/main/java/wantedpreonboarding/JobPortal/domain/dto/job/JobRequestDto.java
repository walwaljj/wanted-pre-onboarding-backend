package wantedpreonboarding.JobPortal.domain.dto.job;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobRequestDto {

    @Column(name = "company_id")
    private Integer companyId; // 회사 id, 기업 정보 확인을 위함.

    private String position; // 포지션

    private Integer compensation; // 채용 보상금

    private String description; // 직무 설명

    private String technologyStack; // 기술

    private String country; // 국가

    private String region; // 지역

}
