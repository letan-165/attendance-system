package cloud.project.attendance.entity;

import cloud.project.attendance.common.enums.ActionLogType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityLog {
    @Id
     String id;
     String userId;
     ActionLogType type;
     String description;

     @CreatedDate
     LocalDateTime createdAt;
}
