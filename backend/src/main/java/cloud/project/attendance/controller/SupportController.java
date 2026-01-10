package cloud.project.attendance.controller;

import cloud.project.attendance.common.ApiResponse;
import cloud.project.attendance.common.enums.SupportStatus;
import cloud.project.attendance.entity.Support;
import cloud.project.attendance.service.SupportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/support")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SupportController {
    SupportService supportService;

    @PostMapping
    ApiResponse<Support> createRequest(
            @RequestParam String attendanceId,
            @RequestParam String reason
    ) {
        return ApiResponse.<Support>builder()
                .message("Gửi yêu cầu điều chỉnh chấm công")
                .result(supportService.createRequest(attendanceId, reason))
                .build();
    }

    @GetMapping("/attendance/{attendanceId}")
    ApiResponse<List<Support>> getByAttendance(@PathVariable String attendanceId) {
        return ApiResponse.<List<Support>>builder()
                .result(supportService.getByAttendance(attendanceId))
                .build();
    }

    @GetMapping("/pending")
    ApiResponse<List<Support>> getPending() {
        return ApiResponse.<List<Support>>builder()
                .result(supportService.getPendingRequests())
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<Support> handleRequest(
            @PathVariable String id,
            @RequestParam SupportStatus status
    ) {
        return ApiResponse.<Support>builder()
                .message("Xử lý yêu cầu điều chỉnh")
                .result(supportService.handleRequest(id, status))
                .build();
    }
}
