package cloud.project.attendance.repository;

import cloud.project.attendance.entity.ActivityLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityLogRepository extends MongoRepository<ActivityLog, String> {
}
