package cloud.project.attendance.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ENUM_INVALID(2001,"Enum not define", HttpStatus.BAD_REQUEST),
    SUPPORT_INVALID(2002,"Support not included (PENDING, APPROVED,REJECTED) ", HttpStatus.BAD_REQUEST),
    ATTENDANCE_INVALID(2003,"Attendance not included (ON_TIME, LATE, EARLY_LEAVE, ABSENT) ", HttpStatus.BAD_REQUEST),
    ACTION_LOG_INVALID(2004,"Action Log not included (AUTH, ATTENDANCE, SUPPORT) ", HttpStatus.BAD_REQUEST),

    TOKEN_LOGOUT(1006,"Token had logout", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1005,"Password don't valid", HttpStatus.BAD_REQUEST),
    AUTHENTICATION(1004,"Token not authentication ", HttpStatus.UNAUTHORIZED),
    AUTHORIZED(1003,"You don't have permission", HttpStatus.FORBIDDEN),
    USER_EXISTS(1002,"User existed", HttpStatus.BAD_REQUEST),
    USER_NO_EXISTS(1001,"User not exists", HttpStatus.BAD_REQUEST),
    OTHER_ERROL(9999,"Other errol", HttpStatus.INTERNAL_SERVER_ERROR);

    int code;
    String message;
    HttpStatusCode httpStatus;
}
