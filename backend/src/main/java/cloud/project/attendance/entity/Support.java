package cloud.project.attendance.entity;

import cloud.project.attendance.common.enums.SupportStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Support {
    @Id
    String id;
    String userId;
    String attendanceId;
    LocalDateTime time;
    String reason;
    SupportStatus status;
}
