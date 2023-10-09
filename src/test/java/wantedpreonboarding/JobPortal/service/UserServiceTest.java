package wantedpreonboarding.JobPortal.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import wantedpreonboarding.JobPortal.SetData;
import wantedpreonboarding.JobPortal.domain.entity.Job;
import wantedpreonboarding.JobPortal.exception.CustomException;
import wantedpreonboarding.JobPortal.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class UserServiceTest extends SetData {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    JobService jobService;

    @DisplayName("사용자 채용 지원 테스트")
    @Test
    void 채용_지원() {

        // given
        Job job = new Job(4, 1, "카카오", "채용 지원 test 포지션", 100000, "Dev 포지션", "Java", "KOR", "PUS", new ArrayList<>());
        jobService.create(job);

        int userId = 1;

        // when
        userService.support(userId, 1);
        userService.support(userId, 4);

        // then
        List<Job> userSupportList = userRepository.findById(userId).get().getSupportList();

        assertThat(userSupportList.size()).isEqualTo(2); // 1번, 4번 채용 공고 지원
        assertThat(userSupportList.get(1).getPosition()).isEqualTo("채용 지원 test 포지션"); // 4번의 포지션과 동일한지 확인
    }

    @DisplayName("사용자 채용 지원 실패 테스트")
    @Test
    void 채용_지원_실패() {

        // given
        Job job = new Job(4, 1, "카카오", "채용 지원 test 포지션", 100000, "Dev 포지션", "Java", "KOR", "PUS", new ArrayList<>());
        jobService.create(job);

        int userId = 1;

        // when, then
        userService.support(userId, 4);  // 4번 공고에 지원
        
        CustomException exception = assertThrows(CustomException.class, () -> userService.support(userId, 4)); // 4번 공고에 중복 지원 시도 
        assertEquals("같은 채용 공고에 중복 지원할 수 없습니다.", exception.getMessage()); // 반환 메시지가 동일한지 확인
        
        List<Job> userSupportList = userRepository.findById(userId).get().getSupportList(); // 중복 지원 시도 후 사용자의 supportList 확인

        assertThat(userSupportList.size()).isEqualTo(1); // 중복 지원 되지 않음
    }
}