package cloud.project.attendance.dto.response;

import cloud.project.attendance.common.enums.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String userID;
    String username;
    String name;
    String email;
    UserRole role;
}
