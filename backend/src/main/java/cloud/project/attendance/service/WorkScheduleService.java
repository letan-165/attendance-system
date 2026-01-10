package cloud.project.attendance.service;

import cloud.project.attendance.entity.WorkSchedule;
import cloud.project.attendance.repository.WorkScheduleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class WorkScheduleService {
    WorkScheduleRepository workScheduleRepository;

    public WorkSchedule create(LocalTime startTime, LocalTime endTime) {
        return workScheduleRepository.save(
                WorkSchedule.builder()
                        .startTime(startTime)
                        .endTime(endTime)
                        .build()
        );
    }

    public List<WorkSchedule> getHistory() {
        return workScheduleRepository.findAllByOrderByCreatedAtDesc();
    }
}
