package cloud.project.attendance.entity;

import cloud.project.attendance.common.enums.UserRole;
import cloud.project.attendance.common.enums.UserStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    String id;
    String username;
    String password;
    String fullName;
    String email;
    UserStatus status;
    UserRole role;
}
