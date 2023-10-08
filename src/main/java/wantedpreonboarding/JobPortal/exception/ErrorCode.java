package wantedpreonboarding.JobPortal.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ErrorCode {

    // COMPANY
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "회사를 찾을 수 없습니다."),

    // JOB
    JOB_NOT_FOUND(HttpStatus.NOT_FOUND, "채용 공고를 찾을 수 없습니다."),
    SEARCH_NOT_FOUND(HttpStatus.NOT_FOUND, "검색 결과가 없습니다."),
    INVALID_COMPANY_INFO(HttpStatus.BAD_REQUEST, "입력된 회사 정보가 잘못되었습니다."),

    // USER
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    DUPLICATE_SUPPORT(HttpStatus.CONFLICT, "같은 채용 공고에 중복 지원할 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}

