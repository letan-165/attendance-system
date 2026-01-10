package cloud.project.attendance.mapper;



import cloud.project.attendance.dto.request.UserSignUpRequest;
import cloud.project.attendance.dto.response.UserResponse;
import cloud.project.attendance.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", ignore = true)
    User toUser(UserSignUpRequest request);
    UserResponse toUserResponse(User user);

}
