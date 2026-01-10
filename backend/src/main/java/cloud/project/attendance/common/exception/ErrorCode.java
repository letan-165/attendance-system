package cloud.project.attendance.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ATTENDANCE_NO_EXISTS(3006,"Attendance not exists", HttpStatus.BAD_REQUEST),
    NOT_CHECKED_IN(3005,"User have not check-in", HttpStatus.BAD_REQUEST),
    ALREADY_CHECKED_OUT(3004,"Check-out existed", HttpStatus.BAD_REQUEST),
    ALREADY_CHECKED_IN(3003,"Check-in existed", HttpStatus.BAD_REQUEST),
    USER_EXISTS(3002,"User existed", HttpStatus.BAD_REQUEST),
    USER_NO_EXISTS(3001,"User not exists", HttpStatus.BAD_REQUEST),

    USER_ROLE_INVALID(2005,"User not included (STAFF, ADMIN) ", HttpStatus.BAD_REQUEST),
    USER_STATUS_INVALID(2005,"User not included (ACTIVE, INACTIVE, BLOCKED) ", HttpStatus.BAD_REQUEST),
    ACTION_LOG_INVALID(2004,"Action Log not included (AUTH, ATTENDANCE, SUPPORT) ", HttpStatus.BAD_REQUEST),
    ATTENDANCE_INVALID(2003,"Attendance not included (ON_TIME, LATE, EARLY_LEAVE, ABSENT) ", HttpStatus.BAD_REQUEST),
    SUPPORT_INVALID(2002,"Support not included (PENDING, APPROVED,REJECTED) ", HttpStatus.BAD_REQUEST),
    ENUM_INVALID(2001,"Enum not define", HttpStatus.BAD_REQUEST),

    TOKEN_LOGOUT(1004,"Token had logout", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003,"Password don't valid", HttpStatus.BAD_REQUEST),
    AUTHENTICATION(1002,"Token not authentication ", HttpStatus.UNAUTHORIZED),
    AUTHORIZED(1001,"You don't have permission", HttpStatus.FORBIDDEN),
    OTHER_ERROL(9999,"Other errol", HttpStatus.INTERNAL_SERVER_ERROR);

    int code;
    String message;
    HttpStatusCode httpStatus;
}
