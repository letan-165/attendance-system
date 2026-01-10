package cloud.project.attendance.repository;

import cloud.project.attendance.common.enums.SupportStatus;
import cloud.project.attendance.entity.Support;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportRepository extends MongoRepository<Support, String> {
    List<Support> findByStatus(SupportStatus status);
    List<Support> findByAttendanceId(String attendanceId);
}

