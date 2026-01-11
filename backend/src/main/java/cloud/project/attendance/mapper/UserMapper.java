package cloud.project.attendance.mapper;

import cloud.project.attendance.dto.response.UserResponse;
import cloud.project.attendance.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);

}
