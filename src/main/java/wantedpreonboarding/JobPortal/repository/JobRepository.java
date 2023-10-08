package wantedpreonboarding.JobPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wantedpreonboarding.JobPortal.domain.entity.Job;

import java.util.Set;

public interface JobRepository extends JpaRepository<Job, Integer> {
    Set<Job> findAllByPosition(String searchWord); // position 으로 job 찾기
    Set<Job> findAllByCompanyNameContains(String searchWord); // searchWord 가 포함 된 CompanyName
}
