package wantedpreonboarding.JobPortal.domain.dto.job;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import wantedpreonboarding.JobPortal.domain.entity.Job;

/**
 * 채용 공고 상세 조회 시 사용
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobDetailResponseDto {

    private Integer id; // 채용 공고 id

    private String companyName;

    private String position; // 포지션

    private String technologyStack; // 기술

    private String country; // 국가

    private String region; // 지역

    private Integer compensation; // 채용 보상금

    private String description; // 직무 설명 - 전체 조회 시 설명 제외 , 상세 조회 시 포함

    @Setter
    private String otherJobInfo;

    public static JobDetailResponseDto of(Job job){
        return JobDetailResponseDto.builder()
                .id(job.getId())
                .companyName(job.getCompanyName())
                .position(job.getPosition())
                .technologyStack(job.getTechnologyStack())
                .description(job.getDescription())
                .country(job.getCountry())
                .region(job.getRegion())
                .compensation(job.getCompensation())
                .description(job.getDescription())
                .otherJobInfo("")
                .build();

    }

}
