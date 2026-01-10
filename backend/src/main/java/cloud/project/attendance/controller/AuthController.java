package cloud.project.attendance.controller;

import cloud.project.attendance.common.ApiResponse;
import cloud.project.attendance.dto.request.LoginRequest;
import cloud.project.attendance.dto.response.LoginResponse;
import cloud.project.attendance.service.AuthService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthController {
    AuthService authService;

    @PostMapping("/public/login")
    ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) throws JOSEException {
        return ApiResponse.<LoginResponse>builder()
                .message("Xác nhận đăng nhập tài khoản: "+request.getUsername())
                .result(authService.login(request))
                .build();
    }

}
