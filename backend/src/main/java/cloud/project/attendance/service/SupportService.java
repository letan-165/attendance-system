package cloud.project.attendance.service;

import cloud.project.attendance.common.enums.SupportStatus;
import cloud.project.attendance.common.exception.AppException;
import cloud.project.attendance.common.exception.ErrorCode;
import cloud.project.attendance.entity.Attendance;
import cloud.project.attendance.entity.Support;
import cloud.project.attendance.repository.AttendanceRepository;
import cloud.project.attendance.repository.SupportRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SupportService {
    SupportRepository supportRepository;
    AttendanceRepository attendanceRepository;

    public Support submitSupport(String attendanceId, String reason) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new AppException(ErrorCode.ATTENDANCE_NO_EXISTS));
        return supportRepository.save(
                Support.builder()
                        .attendanceId(attendance.getId())
                        .reason(reason)
                        .time(LocalDateTime.now())
                        .status(SupportStatus.PENDING)
                        .build()
        );
    }

    public List<Support> getByAttendance(String attendanceId) {
        return supportRepository.findByAttendanceId(attendanceId);
    }

    public List<Support> getPendingRequests() {
        return supportRepository.findByStatus(SupportStatus.PENDING);
    }

    public Support handleRequest(String supportId, SupportStatus status) {
        Support support = supportRepository.findById(supportId)
                .orElseThrow(() -> new AppException(ErrorCode.SUPPORT_INVALID));

        if (status != SupportStatus.PENDING)
            throw new AppException(ErrorCode.SUPPORT_NO_PENDING);

        support.setStatus(status);

        return supportRepository.save(support);
    }
}
