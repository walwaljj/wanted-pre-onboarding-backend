package wantedpreonboarding.JobPortal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wantedpreonboarding.JobPortal.domain.dto.job.JobDetailResponseDto;
import wantedpreonboarding.JobPortal.domain.dto.job.JobRequestDto;
import wantedpreonboarding.JobPortal.domain.dto.job.JobResponseDto;
import wantedpreonboarding.JobPortal.domain.entity.Company;
import wantedpreonboarding.JobPortal.domain.entity.Job;
import wantedpreonboarding.JobPortal.exception.CustomException;
import wantedpreonboarding.JobPortal.exception.ErrorCode;
import wantedpreonboarding.JobPortal.repository.CompanyRepository;
import wantedpreonboarding.JobPortal.repository.JobRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    /**
     * job 생성
     */
    public JobResponseDto create(Job job) {

        Company company = getCompany(job);
        company.getJobList().add(job);

        return JobResponseDto.of(jobRepository.save(job));
    }

    /**
     * company 정보 가져오기
     */
    public Company getCompany(Job job) {

        Integer companyId = job.getCompanyId();
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CustomException(ErrorCode.COMPANY_NOT_FOUND));

        return company;
    }

    /**
     * job 상세 조회
     */
    public JobDetailResponseDto detail(Integer jobId) {

        Job job = findJobById(jobId);

        JobDetailResponseDto jobDetailResponseDto = JobDetailResponseDto.of(job);

        return jobDetailResponseDto;
    }

    /**
     * job 전체 조회
     */
    public List<JobResponseDto> readAll() {

        return jobRepository.findAll().stream().map(JobResponseDto::of).toList();
    }

    /**
     * jobId로 공고 찾기
     */
    public Job findJobById(Integer jobId) {
        return jobRepository.findById(jobId).orElseThrow(() -> new CustomException(ErrorCode.JOB_NOT_FOUND));
    }

    /**
     * job 삭제
     */
    public void delete(Integer jobId) {

        Job job = findJobById(jobId);

        Company company = getCompany(job);
        company.getJobList().remove(job);

        jobRepository.delete(job);
    }

    /**
     * job 수정
     */
    public JobDetailResponseDto update(Integer jobId, JobRequestDto dto) {

        Job job = jobRepository.findById(jobId).get();
        job.jobUpdate(dto);

        return JobDetailResponseDto.of(jobRepository.save(job));
    }



}
