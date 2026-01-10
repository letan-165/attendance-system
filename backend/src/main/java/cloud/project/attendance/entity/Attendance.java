package cloud.project.attendance.entity;

import cloud.project.attendance.common.enums.AttendanceStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {
    @Id
     String id;
     String userId;

     LocalDate workDate;
     LocalDateTime checkInTime;
     LocalDateTime checkOutTime;
     AttendanceStatus status;

     @LastModifiedDate
     LocalDateTime updatedAt;

}