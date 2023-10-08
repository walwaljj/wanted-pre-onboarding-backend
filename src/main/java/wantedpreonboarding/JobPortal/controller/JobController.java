package wantedpreonboarding.JobPortal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wantedpreonboarding.JobPortal.domain.dto.job.JobRequestDto;
import wantedpreonboarding.JobPortal.domain.entity.Job;
import wantedpreonboarding.JobPortal.domain.result.ResResult;
import wantedpreonboarding.JobPortal.domain.result.ResponseCode;
import wantedpreonboarding.JobPortal.service.JobService;

@RestController
@RequestMapping("jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    /**
     * 채용 정보 생성
     */
    @PostMapping("/create")
    public ResponseEntity<ResResult> jobCreate(@RequestBody Job job) {

        ResponseCode responseCode = ResponseCode.JOB_POSTING_CREATE;

        return ResponseEntity.ok(
                ResResult.builder()
                        .responseCode(responseCode)
                        .code(responseCode.getCode())
                        .message(responseCode.getMessage())
                        .data(jobService.create(job))
                        .build());
    }

    /**
     * 채용 상세 조회
     */
    @GetMapping("/{jobId}")
    public ResponseEntity<ResResult> jobDetail(@PathVariable Integer jobId) {

        ResponseCode responseCode = ResponseCode.JOB_POSTING_DETAIL;

        return ResponseEntity.ok(
                ResResult.builder()
                        .responseCode(responseCode)
                        .code(responseCode.getCode())
                        .message(responseCode.getMessage())
                        .data(jobService.detail(jobId))
                        .build());
    }

    /**
     * 채용 전체 조회
     */
    @GetMapping("")
    public ResponseEntity<ResResult> jobReadAll() {

        ResponseCode responseCode = ResponseCode.JOB_POSTING_READ_ALL;

        return ResponseEntity.ok(
                ResResult.builder()
                        .responseCode(responseCode)
                        .code(responseCode.getCode())
                        .message(responseCode.getMessage())
                        .data(jobService.readAll())
                        .build());
    }

    /**
     * 채용 정보 수정
     */
    @PutMapping("/{jobId}")
    public ResponseEntity<ResResult> jobUpdate(@PathVariable Integer jobId, @RequestBody JobRequestDto dto) {

        ResponseCode responseCode = ResponseCode.JOB_POSTING_UPDATE;

        return ResponseEntity.ok(
                ResResult.builder()
                        .responseCode(responseCode)
                        .code(responseCode.getCode())
                        .message(responseCode.getMessage())
                        .data(jobService.update(jobId, dto))
                        .build());
    }

    /**
     * 채용 정보 삭제
     */
    @DeleteMapping("/{jobId}")
    public ResponseEntity<ResResult> jobDelete(@PathVariable Integer jobId) {

        jobService.delete(jobId);

        ResponseCode responseCode = ResponseCode.JOB_POSTING_DELETE;

        return ResponseEntity.ok(
                ResResult.builder()
                        .responseCode(responseCode)
                        .code(responseCode.getCode())
                        .message(responseCode.getMessage())
                        .build());
    }


}
