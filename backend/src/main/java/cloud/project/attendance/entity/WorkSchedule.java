package cloud.project.attendance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkSchedule {
    @Id
    String id;
    LocalTime startTime;
    LocalTime endTime;

    @CreatedDate
    LocalDateTime createdAt;
}
