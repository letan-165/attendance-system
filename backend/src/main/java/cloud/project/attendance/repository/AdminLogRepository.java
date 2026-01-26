package cloud.project.attendance.repository;

import cloud.project.attendance.entity.AdminLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminLogRepository extends MongoRepository<AdminLog, String> {
    List<AdminLog> findAllByOrderByCreatedAtDesc();
    void deleteByIdIn(List<String> ids);
}
