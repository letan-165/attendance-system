package cloud.project.attendance.repository;

import cloud.project.attendance.entity.WorkSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkScheduleRepository extends MongoRepository<WorkSchedule, String> {
    List<WorkSchedule> findAllByOrderByCreatedAtDesc();
    Optional<WorkSchedule> findFirstByOrderByCreatedAtDesc();
}
