package cloud.project.attendance.service;

import cloud.project.attendance.entity.ActivityLog;
import cloud.project.attendance.entity.AdminLog;
import cloud.project.attendance.repository.ActivityLogRepository;
import cloud.project.attendance.repository.AdminLogRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class LogService {
    ActivityLogRepository activityLogRepository;
    AdminLogRepository adminLogRepository;

    public void adminSaveHealth(AdminLog log){
        adminLogRepository.save(log);

        List<AdminLog> logs = adminLogRepository.findAllByOrderByCreatedAtDesc();

        if (logs.size() > 25) {
            List<String> idsToDelete = logs.stream()
                    .skip(25)
                    .map(AdminLog::getId)
                    .toList();

            adminLogRepository.deleteByIdIn(idsToDelete);
        }
    }

    public void userSaveAction(ActivityLog log){
        activityLogRepository.save(log);

        List<AdminLog> logs = activityLogRepository.findAllByOrderByCreatedAtDesc();

        if (logs.size() > 25) {
            List<String> idsToDelete = logs.stream()
                    .skip(25)
                    .map(AdminLog::getId)
                    .toList();

            activityLogRepository.deleteByIdIn(idsToDelete);
        }
    }
}
