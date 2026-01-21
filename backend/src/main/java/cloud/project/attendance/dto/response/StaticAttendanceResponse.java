package cloud.project.attendance.dto.response;

import cloud.project.attendance.entity.Attendance;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StaticAttendanceResponse {
    List<Attendance> attendances;
    long onTimeCount;
    long lateCount;
    long earlyLeaveCount;
    long absentCount;
    long totalWorking;
}

