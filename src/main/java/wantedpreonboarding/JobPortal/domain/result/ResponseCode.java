package wantedpreonboarding.JobPortal.domain.result;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@ToString
public enum ResponseCode {

    // JOB
    JOB_POSTING_CREATE(HttpStatus.OK, "200", "채용 공고 등록 완료"),
    JOB_POSTING_READ_ALL(HttpStatus.OK, "200", "채용 공고 전체 조회 완료"),
    JOB_POSTING_UPDATE(HttpStatus.OK, "200", "채용 공고 수정 완료"),
    JOB_POSTING_DETAIL(HttpStatus.OK, "200", "채용 공고 상세 조회 완료"),
    JOB_POSTING_SEARCH(HttpStatus.OK, "200", "채용 공고 검색 완료"),
    JOB_POSTING_DELETE(HttpStatus.OK, "201", "채용 공고 삭제 완료"),

    // USER
    USER_SUPPORT(HttpStatus.OK, "200", "사용자 채용 지원 완료");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ResponseCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public ResponseEntity<ResResult> toResponse(Object data) {
        return new ResponseEntity<>(ResResult.builder()
                .responseCode(this)
                .code(this.code)
                .message(this.message)
                .data(data)
                .build(), HttpStatus.OK);
    }
}
