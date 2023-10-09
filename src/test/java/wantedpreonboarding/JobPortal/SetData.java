package wantedpreonboarding.JobPortal;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import wantedpreonboarding.JobPortal.domain.entity.Company;
import wantedpreonboarding.JobPortal.domain.entity.Job;
import wantedpreonboarding.JobPortal.repository.JobRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class SetData {

    @Autowired
    protected JobRepository jobRepository;
    protected Map<Integer, Company> companyMap = new HashMap<>();

    public static Integer companySequence = 0;
    public static Integer jobSequence = 0;

    public static Integer getCompanySequence() {
        return ++companySequence;
    }

    public static Integer getJobSequence() {
        return ++jobSequence;
    }

    @BeforeEach
    public void init() {
        Company company = new Company(getCompanySequence(), "카카오", new ArrayList<>());
        companyMap.put(company.getId(), company);

        jobRepository.save(new Job(getJobSequence(), 1, "카카오", "BE", 100000, "BE 포지션", "Java", "KOR", "PUS", new ArrayList<>()));
        jobRepository.save(new Job(getJobSequence(), 1, "카카오", "FE", 200000, "FE 포지션", "JavaScript", "KOR", "PUS", new ArrayList<>()));
        jobRepository.save(new Job(getJobSequence(), 1, "카카오", "BE", 200000, "FE 포지션", "Python", "KOR", "PUS", new ArrayList<>()));
    }
}
