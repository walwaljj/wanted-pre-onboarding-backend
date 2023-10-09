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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import wantedpreonboarding.JobPortal.SetData;
import wantedpreonboarding.JobPortal.domain.dto.job.JobRequestDto;
import wantedpreonboarding.JobPortal.domain.dto.job.JobResponseDto;
import wantedpreonboarding.JobPortal.domain.entity.Job;
import wantedpreonboarding.JobPortal.repository.JobRepository;
import wantedpreonboarding.JobPortal.service.JobService;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
class JobControllerTest extends SetData {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    JobService jobService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    JobRepository jobRepository;
    @DisplayName("공고 저장 테스트")
    @Test
    void 저장() throws Exception {

        // given
        Job job = new Job(4, 1, "카카오", "채용 지원 test 포지션", 100000, "Dev 포지션", "Java", "KOR", "PUS", new ArrayList<>());

        String json = objectMapper.writeValueAsString(job);

        // when
        mockMvc.perform(post("/jobs/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

        // then
        assertEquals(4, jobRepository.findAll().size());

        assertThat(jobRepository.findById(4).get().getPosition()).isEqualTo("채용 지원 test 포지션");
    }

    @DisplayName("전체 조회 테스트")
    @Test
    void 전체_조회() throws Exception {

        // given
        Job job = new Job(4, 1, "네이버", "BE", 100000, "BE", "Java", "KOR", "PUS", new ArrayList<>());
        jobRepository.save(job);

        //when, then
        mockMvc.perform(get("/jobs")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[3].companyName").value("네이버"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(4))
                .andDo(print());
    }

    @DisplayName("검색 테스트")
    @Test
    void 검색() throws Exception {

        //when, then
        mockMvc.perform(get("/jobs/search")
                        .param("searchWord", "BE")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].companyName").value("카카오"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].position").value("BE"))
                .andDo(print());
    }
    @DisplayName("수정 테스트")
    @Test
    void 수정() throws Exception {
        // given
        Job job = new Job(4, 1, "카카오", "BE", 100000, "BE 포지션", "Java", "KOR", "PUS", new ArrayList<>());
        JobResponseDto jobResponseDto = jobService.create(job);

        JobRequestDto requestDto = new JobRequestDto(1, "FE", 200000, "FE 포지션", "Javascript", "KOR", "PUS");
        String json = objectMapper.writeValueAsString(requestDto);

        // when
        mockMvc.perform(put("/jobs/{jobId}",4)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

        // then
        assertThat(jobRepository.findById(jobResponseDto.getId()).get().getPosition()).isEqualTo("FE");
        assertThat(jobRepository.findById(jobResponseDto.getId()).get().getCompensation()).isEqualTo(200000);
        assertThat(jobRepository.findById(jobResponseDto.getId()).get().getDescription()).isEqualTo("FE 포지션");
        assertThat(jobRepository.findById(jobResponseDto.getId()).get().getTechnologyStack()).isEqualTo("Javascript");
    }

    @DisplayName("삭제 테스트")
    @Test
    void 공고_삭제() throws Exception {

        //when
        mockMvc.perform(delete("/jobs/{jobId}",1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());

        // then
        assertEquals(2, jobRepository.findAll().size()); // init 데이터에서 공고 1개 삭제됨.

    }
}