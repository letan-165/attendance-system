package cloud.project.attendance.service;

import cloud.project.attendance.common.exception.AppException;
import cloud.project.attendance.common.exception.ErrorCode;
import cloud.project.attendance.dto.request.LoginRequest;
import cloud.project.attendance.dto.request.TokenRequest;
import cloud.project.attendance.dto.response.LoginResponse;
import cloud.project.attendance.entity.User;
import cloud.project.attendance.mapper.UserMapper;
import cloud.project.attendance.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthService {

    @NonFinal
    @Value("${key.jwt.value}")
    String KEY;

    @NonFinal
    @Value("${app.time.expiryTime}")
    int expiryTime;

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) throws JOSEException {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NO_EXISTS));

        boolean check = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!check)
            throw new AppException(ErrorCode.PASSWORD_INVALID);

        return LoginResponse.builder()
                .userID(user.getId())
                .name(user.getFullName())
                .token(generate(user))
                .build();
    }

    String generate(User user) throws JOSEException {
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(UUID.randomUUID().toString())
                .issuer("ATTENDANCE")
                .subject(user.getFullName())
                .issueTime(Date.from(Instant.now()))
                .expirationTime(Date.from(Instant.now().plus(expiryTime,ChronoUnit.SECONDS)))
                .claim("scope", user.getRole())
                .build();

        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader,payload);
        jwsObject.sign(new MACSigner(KEY.getBytes()));

        return jwsObject.serialize();
    }
}
