package wantedpreonboarding.JobPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wantedpreonboarding.JobPortal.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
