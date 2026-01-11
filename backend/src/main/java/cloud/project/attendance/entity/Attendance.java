package cloud.project.attendance.entity;

import cloud.project.attendance.common.enums.AttendanceStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attendance {
    @Id
    String id;
    String userId;

    LocalDate workDate;
    LocalDateTime checkInTime;
    LocalDateTime checkOutTime;
    List<AttendanceStatus> status;

    @CreatedDate
    LocalDateTime createdAt;
}