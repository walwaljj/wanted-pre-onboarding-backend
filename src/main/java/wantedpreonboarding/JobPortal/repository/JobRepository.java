package wantedpreonboarding.JobPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wantedpreonboarding.JobPortal.domain.entity.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {
}
