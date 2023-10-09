package wantedpreonboarding.JobPortal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import wantedpreonboarding.JobPortal.SetData;
import wantedpreonboarding.JobPortal.domain.entity.Job;
import wantedpreonboarding.JobPortal.repository.JobRepository;
import wantedpreonboarding.JobPortal.repository.UserRepository;
import wantedpreonboarding.JobPortal.service.JobService;
import wantedpreonboarding.JobPortal.service.UserService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest extends SetData {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    JobService jobService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @DisplayName("사용자 채용 지원 테스트")
    @Test
    void 채용_지원() throws Exception {

        // given
        int userId = 1;

        // when
        mockMvc.perform(post("/users/{userId}/support", 1)
                        .param("jobId", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andDo(print());

        // then
        List<Job> userSupportList = userRepository.findById(userId).get().getSupportList();

        assertThat(userSupportList.size()).isEqualTo(1); // 지원자가 채용공고 1에 지원함.
        assertThat(userSupportList.get(0).getPosition()).isEqualTo("BE"); // 지원한 포지션 BE.

    }
}