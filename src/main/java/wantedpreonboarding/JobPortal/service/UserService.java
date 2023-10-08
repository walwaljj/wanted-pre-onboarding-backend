package wantedpreonboarding.JobPortal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wantedpreonboarding.JobPortal.domain.dto.user.UserSupportResponseDto;
import wantedpreonboarding.JobPortal.domain.entity.Job;
import wantedpreonboarding.JobPortal.domain.entity.User;
import wantedpreonboarding.JobPortal.exception.CustomException;
import wantedpreonboarding.JobPortal.exception.ErrorCode;
import wantedpreonboarding.JobPortal.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final JobService jobService;

    /**
     * 사용자가 공고에 지원함.
     */
    public UserSupportResponseDto support(Integer userId, Integer jobId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Job job = jobService.findJobById(jobId);

        // 사용자가 지원한 지원 공고를 가져옴.
        List<Job> supportList = user.getSupportList();

        // 지원 여부 확인
        for (Job support : supportList) {
            if (support.getId().equals(jobId))
                throw new CustomException(ErrorCode.DUPLICATE_SUPPORT);
        }

        // 사용자 지원 공고에 추가함.
        supportList.add(job);

        return UserSupportResponseDto.of(userRepository.save(user), jobId);
    }
}
