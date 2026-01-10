package cloud.project.attendance.repository;

import cloud.project.attendance.entity.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends MongoRepository<Attendance, String> {
    Optional<Attendance> findByUserIdAndWorkDate(String userId, LocalDate workDate);
    List<Attendance> findByUserIdOrderByWorkDateDesc(String userId);
}
