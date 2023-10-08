package wantedpreonboarding.JobPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wantedpreonboarding.JobPortal.domain.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
