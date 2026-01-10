package cloud.project.attendance.service;

import cloud.project.attendance.common.exception.AppException;
import cloud.project.attendance.common.exception.ErrorCode;
import cloud.project.attendance.dto.response.UserResponse;
import cloud.project.attendance.entity.User;
import cloud.project.attendance.mapper.UserMapper;
import cloud.project.attendance.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public List<UserResponse> findAll(){
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }


}
