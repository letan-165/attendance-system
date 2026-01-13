package cloud.project.attendance.dto.response;

import cloud.project.attendance.common.enums.UserRole;
import cloud.project.attendance.common.enums.UserStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String fullName;
    String email;
    UserStatus status;
    UserRole role;
}
