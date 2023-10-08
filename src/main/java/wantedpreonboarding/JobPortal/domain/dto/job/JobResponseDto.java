package wantedpreonboarding.JobPortal.domain.dto.job;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wantedpreonboarding.JobPortal.domain.entity.Job;

/**
 * 채용 공고 전체 조회 시 사용
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobResponseDto {

    private Integer id;

    private String companyName;

    private String position; // 포지션

    private Integer compensation; // 채용 보상금

    private String technologyStack; // 기술

    private String country; // 국가

    private String region; // 지역

    public static JobResponseDto of(Job job) {
        return JobResponseDto.builder()
                .id(job.getId())
                .companyName(job.getCompanyName())
                .position(job.getPosition())
                .compensation(job.getCompensation())
                .technologyStack(job.getTechnologyStack())
                .country(job.getCountry())
                .region(job.getRegion())
                .build();

    }
}
