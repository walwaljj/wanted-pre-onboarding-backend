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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        if(!company.getCompanyName().equals(job.getCompanyName()) || !company.getId().equals(job.getCompanyId())){
            throw new CustomException(ErrorCode.INVALID_COMPANY_INFO);
        }

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
        List<Job> jobList = getCompany(job).getJobList();

        JobDetailResponseDto jobDetailResponseDto = JobDetailResponseDto.of(job);

        // 해당 공고를 제외하고 보여주기 위해 size > 1
        if (jobList.size() > 1) {
            String showJobList = showJobList(jobId, jobList);
            jobDetailResponseDto.setOtherJobInfo(showJobList);
        } else {
            jobDetailResponseDto.setOtherJobInfo(null); // size가 1보다 작으면 otherJobInfo를 null로 설정
        }

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


    /**
     * 기타 채용 정보 반환
     */
    public String showJobList(Integer jobId, List<Job> jobList) {

        StringBuilder sb = new StringBuilder();

        sb.append("이 회사의 공고 중인 다른 포지션 [ ");

        for (Job job : jobList) {
            Integer getJobId = job.getId();
            if (getJobId.equals(jobId)) continue;
            String position = job.getPosition();
            sb.append(position + " ");
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * 채용 정보 검색
     */
    public Set<JobResponseDto> search(String searchWord) {

        // 결과 중복 시 제외 시키기 위해 set 선언.
        Set<JobResponseDto> jobResponseDtoSet = new HashSet<>();

        Set<JobResponseDto> positionResult = jobRepository.findAllByPosition(searchWord).stream()
                .map(JobResponseDto::of)
                .collect(Collectors.toSet());

        jobResponseDtoSet.addAll(positionResult);

        jobResponseDtoSet.addAll(jobRepository.findAllByCompanyNameContains(searchWord).stream()
                .map(JobResponseDto::of)
                .collect(Collectors.toSet()));

        // 검색 결과가 없는 경우
        if(jobResponseDtoSet.isEmpty()) throw new CustomException(ErrorCode.SEARCH_NOT_FOUND);

        return jobResponseDtoSet;
    }
}
