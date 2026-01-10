package cloud.project.attendance.controller;

import cloud.project.attendance.common.ApiResponse;
import cloud.project.attendance.entity.WorkSchedule;
import cloud.project.attendance.service.WorkScheduleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/work-schedule")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkScheduleController {
    WorkScheduleService workScheduleService;

    @PostMapping
    ApiResponse<WorkSchedule> create(
            @RequestParam LocalTime startTime,
            @RequestParam LocalTime endTime
    ) {
        return ApiResponse.<WorkSchedule>builder()
                .message("Thêm giờ làm việc mới")
                .result(workScheduleService.create(startTime, endTime))
                .build();
    }

    @GetMapping("/history")
    ApiResponse<List<WorkSchedule>> history() {
        return ApiResponse.<List<WorkSchedule>>builder()
                .result(workScheduleService.getHistory())
                .build();
    }
}
