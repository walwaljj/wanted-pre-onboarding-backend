package wantedpreonboarding.JobPortal;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import wantedpreonboarding.JobPortal.domain.entity.Company;
import wantedpreonboarding.JobPortal.domain.entity.Job;
import wantedpreonboarding.JobPortal.domain.entity.User;
import wantedpreonboarding.JobPortal.repository.CompanyRepository;
import wantedpreonboarding.JobPortal.repository.JobRepository;
import wantedpreonboarding.JobPortal.repository.UserRepository;

import java.util.ArrayList;

@SpringBootTest
@ActiveProfiles("test")
public class SetData {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    public static Integer companySequence = 0;
    public static Integer jobSequence = 0;
    public static Integer userSequence = 0;

    public static Integer getCompanySequence() {
        return ++companySequence;
    }

    public static Integer getJobSequence() {
        return ++jobSequence;
    }
    public static Integer getUserSequence() {
        return ++userSequence;
    }

    @BeforeEach
    public void init() {
        Company company = new Company(getCompanySequence(), "카카오", new ArrayList<>());
        companyRepository.save(company);

        jobRepository.save(new Job(getJobSequence(), 1, "카카오", "BE", 100000, "BE 포지션", "Java", "KOR", "PUS", new ArrayList<>()));
        jobRepository.save(new Job(getJobSequence(), 1, "카카오", "FE", 200000, "FE 포지션", "JavaScript", "KOR", "PUS", new ArrayList<>()));
        jobRepository.save(new Job(getJobSequence(), 1, "카카오", "BE", 200000, "FE 포지션", "Python", "KOR", "PUS", new ArrayList<>()));

        userRepository.save(new User(getUserSequence(), "test1", new ArrayList<>()));
        userRepository.save(new User(getUserSequence(), "test2", new ArrayList<>()));
        userRepository.save(new User(getUserSequence(), "test3", new ArrayList<>()));
    }
}
