package cloud.project.attendance.controller;

import cloud.project.attendance.common.ApiResponse;
import cloud.project.attendance.entity.Attendance;
import cloud.project.attendance.service.AttendanceService;
import cloud.project.attendance.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttendanceController {
    AttendanceService attendanceService;
    AuthService authService;

    @PostMapping("/check-in")
    ApiResponse<Attendance> checkIn() {
        String userId = authService.getUserIdFromToken();
        return ApiResponse.<Attendance>builder()
                .message("Check-in thành công")
                .result(attendanceService.checkIn(userId))
                .build();
    }


    @PostMapping("/check-out")
    ApiResponse<Attendance> checkOut() {
        String userId = authService.getUserIdFromToken();
        return ApiResponse.<Attendance>builder()
                .message("Check-out thành công")
                .result(attendanceService.checkOut(userId))
                .build();
    }


    @GetMapping("/today")
    ApiResponse<Attendance> getToday() {
        String userId = authService.getUserIdFromToken();
        return ApiResponse.<Attendance>builder()
                .result(attendanceService.getToday(userId))
                .build();
    }

    @GetMapping("/history")
    ApiResponse<List<Attendance>> history() {
        String userId = authService.getUserIdFromToken();
        return ApiResponse.<List<Attendance>>builder()
                .result(attendanceService.getHistory(userId))
                .build();
    }

}
