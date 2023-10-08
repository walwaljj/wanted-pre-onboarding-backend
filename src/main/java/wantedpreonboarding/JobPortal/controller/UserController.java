package wantedpreonboarding.JobPortal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wantedpreonboarding.JobPortal.domain.result.ResResult;
import wantedpreonboarding.JobPortal.domain.result.ResponseCode;
import wantedpreonboarding.JobPortal.service.UserService;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/{userId}/support")
    public ResponseEntity<ResResult> userSupport(@PathVariable Integer userId, @RequestParam Integer jobId){

        ResponseCode responseCode = ResponseCode.USER_SUPPORT;

        return ResponseEntity.ok(
                ResResult.builder()
                        .responseCode(responseCode)
                        .code(responseCode.getCode())
                        .message(responseCode.getMessage())
                        .data(userService.support(userId,jobId))
                        .build());
    }

}
