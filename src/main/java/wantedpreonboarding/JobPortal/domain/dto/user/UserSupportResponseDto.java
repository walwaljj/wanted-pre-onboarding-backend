package wantedpreonboarding.JobPortal.domain.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wantedpreonboarding.JobPortal.domain.entity.Job;
import wantedpreonboarding.JobPortal.domain.entity.User;

/**
 * 사용자가 지원을 완료한 후 보여줄 dto
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSupportResponseDto {

    private Integer userId;

    private String username;

    private String companyName;

    private String position;

    public static UserSupportResponseDto of(User user, Integer jobId) {

        Job job = user.getSupportList().stream()
                .filter(e -> e.getId().equals(jobId))
                .findFirst().get();

        return UserSupportResponseDto.builder()
                .username(user.getUsername())
                .companyName(job.getCompanyName())
                .position(job.getPosition())
                .build();
    }
}
