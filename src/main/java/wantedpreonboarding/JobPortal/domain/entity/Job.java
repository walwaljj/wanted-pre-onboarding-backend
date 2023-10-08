package wantedpreonboarding.JobPortal.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wantedpreonboarding.JobPortal.domain.dto.job.JobRequestDto;

import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 채용 공고 id

    @Column(name = "company_id")
    private Integer companyId; // 회사 id

    private String companyName; // 회사명

    private String position; // 포지션

    private Integer compensation; // 채용 보상금

    private String description; // 직무 설명

    private String technologyStack; // 기술

    private String country; // 국가

    private String region; // 지역

    @ManyToMany(mappedBy = "supportList")
    private List<User> supportUserList;

    public void jobUpdate(JobRequestDto job) {
        this.position = job.getPosition();
        this.compensation = job.getCompensation();
        this.description = job.getDescription();
        this.technologyStack = job.getTechnologyStack();
        this.country = job.getCountry();
        this.region = job.getRegion();
    }

}
