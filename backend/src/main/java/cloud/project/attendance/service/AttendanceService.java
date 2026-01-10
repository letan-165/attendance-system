package cloud.project.attendance.service;

import cloud.project.attendance.common.enums.AttendanceStatus;
import cloud.project.attendance.common.exception.AppException;
import cloud.project.attendance.common.exception.ErrorCode;
import cloud.project.attendance.entity.Attendance;
import cloud.project.attendance.entity.WorkSchedule;
import cloud.project.attendance.repository.AttendanceRepository;
import cloud.project.attendance.repository.WorkScheduleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AttendanceService {

    AttendanceRepository attendanceRepository;
    WorkScheduleRepository workScheduleRepository;

    WorkSchedule getLatestSchedule() {
        return workScheduleRepository
                .findFirstByOrderByCreatedAtDesc()
                .orElseThrow(() -> new AppException(ErrorCode.OTHER_ERROL));
    }

    public Attendance checkIn(String userId) {
        LocalDate today = LocalDate.now();
        WorkSchedule schedule = getLatestSchedule();

        Attendance attendance = attendanceRepository
                .findByUserIdAndWorkDate(userId, today)
                .orElse(
                        Attendance.builder()
                                .userId(userId)
                                .workDate(today)
                                .build()
                );

        if (attendance.getCheckInTime() != null) {
            throw new AppException(ErrorCode.ALREADY_CHECKED_IN);
        }

        LocalDateTime now = LocalDateTime.now();
        attendance.setCheckInTime(now);

        AttendanceStatus status = now.toLocalTime().isAfter(schedule.getStartTime())
                ? AttendanceStatus.LATE
                : AttendanceStatus.ON_TIME;

        attendance.getStatus().add(status);

        return attendanceRepository.save(attendance);
    }

    public Attendance checkOut(String userId) {
        WorkSchedule schedule = getLatestSchedule();

        Attendance attendance = attendanceRepository
                .findByUserIdAndWorkDate(userId, LocalDate.now())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_CHECKED_IN));

        if (attendance.getCheckOutTime() != null) {
            throw new AppException(ErrorCode.ALREADY_CHECKED_OUT);
        }

        LocalDateTime now = LocalDateTime.now();
        attendance.setCheckOutTime(now);

        if (now.toLocalTime().isBefore(schedule.getEndTime())) {
            attendance.getStatus().add(AttendanceStatus.EARLY_LEAVE);
        }

        return attendanceRepository.save(attendance);
    }

    public Attendance getToday(String userId) {
        return attendanceRepository
                .findByUserIdAndWorkDate(userId, LocalDate.now())
                .orElseThrow(() -> new AppException(ErrorCode.ATTENDANCE_NO_EXISTS));
    }

    public List<Attendance> getHistory(String userId) {
        return attendanceRepository.findByUserIdOrderByWorkDateDesc(userId);
    }
}
