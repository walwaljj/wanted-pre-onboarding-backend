package wantedpreonboarding.JobPortal.domain.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResResult<T> {

    private ResponseCode responseCode;
    private String code;
    private String message;
    private T data;

    @Override
    public String toString() {
        return "ResResult{" +
                "responseCode=" + responseCode +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
