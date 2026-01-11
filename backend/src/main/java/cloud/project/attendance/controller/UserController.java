package cloud.project.attendance.controller;

import cloud.project.attendance.common.ApiResponse;
import cloud.project.attendance.common.enums.UserStatus;
import cloud.project.attendance.dto.response.UserResponse;
import cloud.project.attendance.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping
    ApiResponse<List<UserResponse>> findAll(){
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.findAll())
                .build();
    }

    @PutMapping("/{id}/status")
    ApiResponse<UserResponse> updateStatus(
            @PathVariable String id,
            @RequestParam UserStatus status
    ) {
        return ApiResponse.<UserResponse>builder()
                .message("Cập nhật trạng thái user")
                .result(userService.updateStatus(id, status))
                .build();
    }
}
