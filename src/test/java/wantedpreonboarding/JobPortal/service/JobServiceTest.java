package wantedpreonboarding.JobPortal.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import wantedpreonboarding.JobPortal.SetData;
import wantedpreonboarding.JobPortal.domain.dto.job.JobRequestDto;
import wantedpreonboarding.JobPortal.domain.dto.job.JobResponseDto;
import wantedpreonboarding.JobPortal.domain.entity.Company;
import wantedpreonboarding.JobPortal.domain.entity.Job;
import wantedpreonboarding.JobPortal.exception.CustomException;
import wantedpreonboarding.JobPortal.repository.CompanyRepository;
import wantedpreonboarding.JobPortal.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@ActiveProfiles("test")
class JobServiceTest extends SetData {

    @Autowired
    JobService jobService;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    CompanyRepository companyRepository;

    @DisplayName("공고 저장 테스트")
    @Test
    void 공고_저장() {
        // given
        Integer jobSequence = getJobSequence();
        Job job = new Job(jobSequence, 1, "카카오", "BE", 100000, "BE 포지션", "Java", "KOR", "PUS", new ArrayList<>());

        // when
        JobResponseDto jobResponseDto = jobService.create(job);

        // then
        assertThat(jobRepository.findAll().size()).isEqualTo(4); // jobResponseDto 1 + init data 3
        assertThat(jobRepository.findById(job.getId()).get().getId()).isEqualTo(jobResponseDto.getId()); // 저장 시도한 job 과 저장 후 반환된 id 가 일치한지 확인


    }

    @DisplayName("공고 저장 실패 테스트")
    @Test
    void 공고_저장_실패() {

        // given
        Integer jobSequence = getJobSequence();
        Job job = new Job(jobSequence, 2, "카카오2", "BE", 100000, "BE 포지션", "Java", "KOR", "PUS", new ArrayList<>());

        // when, then
        CustomException exception = assertThrows(CustomException.class, () -> jobService.create(job));
        assertEquals("입력된 회사 정보가 잘못되었습니다.", exception.getMessage()); // 반환 메시지가 동일한지 확인

        assertThat(jobRepository.findAll().size()).isEqualTo(3); // companyId 또는 Name 이 상이해 저장 되지 않음. init 한 데이터 3
    }

    @DisplayName("전체 조회 테스트")
    @Test
    void 전체_조회() {

        // when
        List<JobResponseDto> jobResponseDtoList = jobService.readAll();

        // then
        assertThat(jobResponseDtoList.size()).isEqualTo(3);
    }

    @DisplayName("삭제 테스트")
    @Test
    void 공고_삭제() {

        // given
        Job job = new Job(4, 1, "카카오", "BE", 100000, "BE 포지션", "Java", "KOR", "PUS", new ArrayList<>());
        jobService.create(job);

        // when
        jobService.delete(1);

        // then
        assertThat(jobRepository.findAll().size()).isEqualTo(3);
    }

    @DisplayName("수정 테스트")
    @Test
    void 공고_수정() {

        // given
        Job job = new Job(4, 1, "카카오", "BE", 100000, "BE 포지션", "Java", "KOR", "PUS", new ArrayList<>());
        JobResponseDto jobResponseDto = jobService.create(job);

        // when
        jobService.update(4, new JobRequestDto(1, "FE", 200000, "FE 포지션", "Javascript", "KOR", "PUS"));

        // then
        assertThat(jobRepository.findById(jobResponseDto.getId()).get().getPosition()).isEqualTo("FE");
        assertThat(jobRepository.findById(jobResponseDto.getId()).get().getCompensation()).isEqualTo(200000);
        assertThat(jobRepository.findById(jobResponseDto.getId()).get().getDescription()).isEqualTo("FE 포지션");
        assertThat(jobRepository.findById(jobResponseDto.getId()).get().getTechnologyStack()).isEqualTo("Javascript");
    }

    @DisplayName("수정 실패 테스트")
    @Test
    void 공고_수정_실패() {

        // given
        Job job = new Job(4, 1, "카카오", "BE", 100000, "BE 포지션", "Java", "KOR", "PUS", new ArrayList<>());
        JobResponseDto jobResponseDto = jobService.create(job);

        JobRequestDto updateDto = new JobRequestDto(2, "FE", 200000, "FE 포지션", "Javascript", "KOR", "PUS");

        // when, then
        CustomException exception = assertThrows(CustomException.class, () -> jobService.update(4, updateDto));
        assertEquals("입력된 회사 정보가 잘못되었습니다.", exception.getMessage()); // 반환 메시지가 동일한지 확인

        assertThat(jobRepository.findById(jobResponseDto.getId()).get().getPosition()).isEqualTo("BE"); // 변경되지 않음.
        assertThat(jobRepository.findById(jobResponseDto.getId()).get().getCompensation()).isEqualTo(100000);
    }

    @DisplayName("통합 검색 테스트")
    @Test
    void 검색() {

        // given
        Integer companySequence = getCompanySequence();
        Company company = new Company(companySequence, "네이버", new ArrayList<>());
        companyRepository.save(company);

        Integer jobSequence = getJobSequence();
        Job job = new Job(jobSequence, 2, "네이버", "BE", 100000, "BE 포지션", "Java", "KOR", "PUS", new ArrayList<>());
        jobRepository.save(job);

        // when
        Set<JobResponseDto> findByPosition = jobService.search("BE");
        Set<JobResponseDto> findByCompany = jobService.search("네");
        // then
        assertThat(findByPosition.size()).isEqualTo(3); // 'BE' 가 포함된 회사명 검색 , init data 2 + given data 1 = 3
        assertThat(findByCompany.size()).isEqualTo(1);  // '네' 가 포함된 회사명 검색

    }

    @DisplayName("검색 실패 테스트")
    @Test
    void 검색_실패() {

        // given
        Integer companySequence = getCompanySequence();
        Company company = new Company(companySequence, "네이버", new ArrayList<>());
        companyRepository.save(company);

        Integer jobSequence = getJobSequence();
        Job job = new Job(jobSequence, 2, "네이버", "BE", 100000, "BE 포지션", "Java", "KOR", "PUS", new ArrayList<>());
        jobRepository.save(job);

        // when, then
        CustomException exception = assertThrows(CustomException.class, () -> jobService.search("DEV"));
        assertEquals("검색 결과가 없습니다.", exception.getMessage()); // 반환 메시지가 동일한지 확인

    }


}