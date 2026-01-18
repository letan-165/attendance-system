package cloud.project.attendance.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SUPPORT_NO_EXISTS(3009,"Yêu cầu hỗ trợ không tồn tại", HttpStatus.BAD_REQUEST),
    WORK_SCHEDULE_NO_EXISTS(3008,"Thời gian làm việc không tồn tại", HttpStatus.BAD_REQUEST),
    SUPPORT_PENDING(3007,"Yêu cầu hỗ trợ đã được xử lý", HttpStatus.BAD_REQUEST),
    ATTENDANCE_NO_EXISTS(3006,"Dữ liệu chấm công không tồn tại", HttpStatus.BAD_REQUEST),
    NOT_CHECKED_IN(3005,"Người dùng chưa check-in", HttpStatus.BAD_REQUEST),
    ALREADY_CHECKED_OUT(3004,"Đã check-out trước đó", HttpStatus.BAD_REQUEST),
    ALREADY_CHECKED_IN(3003,"Đã check-in trước đó", HttpStatus.BAD_REQUEST),
    USER_EXISTS(3002,"Người dùng đã tồn tại", HttpStatus.BAD_REQUEST),
    USER_NO_EXISTS(3001,"Người dùng không tồn tại", HttpStatus.BAD_REQUEST),

    USER_ROLE_INVALID(2005,"Role không hợp lệ (STAFF, ADMIN)", HttpStatus.BAD_REQUEST),
    USER_STATUS_INVALID(2005,"Trạng thái người dùng không hợp lệ (ACTIVE, INACTIVE, BLOCKED)", HttpStatus.BAD_REQUEST),
    ACTION_LOG_INVALID(2004,"Action log không hợp lệ (AUTH, ATTENDANCE, SUPPORT)", HttpStatus.BAD_REQUEST),
    ATTENDANCE_INVALID(2003,"Trạng thái chấm công không hợp lệ (ON_TIME, LATE, EARLY_LEAVE, ABSENT)", HttpStatus.BAD_REQUEST),
    SUPPORT_INVALID(2002,"Trạng thái hỗ trợ không hợp lệ (PENDING, APPROVED, REJECTED)", HttpStatus.BAD_REQUEST),
    ENUM_INVALID(2001,"Giá trị enum không được định nghĩa", HttpStatus.BAD_REQUEST),

    TOKEN_INVALID(1004,"Token không hợp lệ", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003,"Mật khẩu không hợp lệ", HttpStatus.BAD_REQUEST),
    AUTHENTICATION(1002,"Token chưa được xác thực", HttpStatus.UNAUTHORIZED),
    AUTHORIZED(1001,"Bạn không có quyền truy cập", HttpStatus.FORBIDDEN),
    OTHER_ERROL(9999,"Lỗi hệ thống chưa định nghĩa", HttpStatus.INTERNAL_SERVER_ERROR);


    int code;
    String message;
    HttpStatusCode httpStatus;
}
