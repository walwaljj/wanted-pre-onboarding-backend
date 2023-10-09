package wantedpreonboarding.JobPortal.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import wantedpreonboarding.JobPortal.SetData;
import wantedpreonboarding.JobPortal.domain.entity.Company;
import wantedpreonboarding.JobPortal.domain.entity.Job;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class JobRepositoryTest extends SetData {

    @Autowired
    JobRepository jobRepository;

    @DisplayName("공고 저장 테스트")
    @Test
    void 공고_저장() {

        // given
        Integer jobSequence = getJobSequence();
        Job job = new Job(jobSequence, 1, "카카오", "BE", 100000, "BE 포지션2", "Java", "KOR", "PUS", new ArrayList<>());

        // when
        Job saveJob = jobRepository.save(job);

        // then
        assertThat(jobRepository.findAll().size()).isEqualTo(4);
        assertThat(jobRepository.findById(jobSequence).get().getId()).isEqualTo(saveJob.getId());


    }

    @DisplayName("포지션 검색 테스트")
    @Test
    void 포지션_검색() {
        // given
        Company company = new Company(getCompanySequence(), "네이버", new ArrayList<>());
        companyMap.put(company.getId(), company);

        Integer jobSequence = getJobSequence();
        Job job = new Job(jobSequence, 2, "네이버", "BE", 100000, "BE 포지션", "Java", "KOR", "PUS", new ArrayList<>());
        jobRepository.save(job);

        // when
        Set<Job> findBE = jobRepository.findAllByPosition("BE");
        Set<Job> findDEV = jobRepository.findAllByPosition("DEV"); // 등록되지 않은 포지션 검색

        // then
        assertThat(findBE.size()).isEqualTo(3); // 카카오 BE 2  +  네이버 BE 1
        assertThat(findDEV.size()).isEqualTo(0); // 등록되지 않은 포지션 검색
    }

    @DisplayName("회사 이름 검색 테스트")
    @Test
    void findAllByCompanyNameContains() {
        // given
        Company company = new Company(getCompanySequence(), "네이버", new ArrayList<>());
        companyMap.put(company.getId(), company);

        Integer jobSequence = getJobSequence();
        Job job = new Job(jobSequence, 2, "네이버", "BE", 100000, "BE 포지션", "Java", "KOR", "PUS", new ArrayList<>());
        jobRepository.save(job);

        // when
        Set<Job> findNE = jobRepository.findAllByCompanyNameContains("네");
        Set<Job> findKAKAKA = jobRepository.findAllByCompanyNameContains("카카카");

        // then
        assertThat(findNE.size()).isEqualTo(1); // '네' 가 포함된 회사명 검색
        assertThat(findKAKAKA.size()).isEqualTo(0);  // 등록되지 않은 회사명 검색
    }
}