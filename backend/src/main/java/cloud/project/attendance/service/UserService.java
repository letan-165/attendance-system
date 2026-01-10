package cloud.project.attendance.service;

import cloud.project.attendance.common.enums.UserRole;
import cloud.project.attendance.common.enums.UserStatus;
import cloud.project.attendance.common.exception.AppException;
import cloud.project.attendance.common.exception.ErrorCode;
import cloud.project.attendance.dto.request.UserSignUpRequest;
import cloud.project.attendance.dto.response.UserResponse;
import cloud.project.attendance.entity.User;
import cloud.project.attendance.mapper.UserMapper;
import cloud.project.attendance.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public List<UserResponse> findAll(){
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public List<UserResponse> createUsers(List<UserSignUpRequest> requests) {
        List<User> users = requests.stream().map(req -> {
            if (userRepository.existsByUsername(req.getUsername())) {
                throw new AppException(ErrorCode.USER_EXISTS);
            }

            return User.builder()
                    .username(req.getUsername())
                    .password(passwordEncoder.encode(req.getPassword()))
                    .fullName(req.getName())
                    .email(req.getEmail())
                    .status(UserStatus.ACTIVE)
                    .role(UserRole.STAFF)
                    .build();
        }).toList();

        return userRepository.saveAll(users)
                .stream().map(userMapper::toUserResponse)
                .toList();
    }

    public UserResponse updateStatus(String userId, UserStatus status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NO_EXISTS));

        user.setStatus(status);
        return userMapper.toUserResponse(userRepository.save(user));
    }
}
