package cloud.project.attendance.entity;

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
     String action;
     String endpoint;

    @Builder.Default
    String serverName = System.getenv().getOrDefault("HOSTNAME", "LOCAL");

     @CreatedDate
     LocalDateTime createdAt;
}
