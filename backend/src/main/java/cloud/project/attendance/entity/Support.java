package cloud.project.attendance.entity;

import cloud.project.attendance.common.enums.SupportStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Support {
    @Id
     String id;
     String attendanceId;
     LocalDateTime time;
     String reason;
     SupportStatus status;
}
