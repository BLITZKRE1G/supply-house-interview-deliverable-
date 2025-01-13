package abhinab.full_stack.spring_assignment.db;

import org.springframework.data.jpa.repository.JpaRepository;

import abhinab.full_stack.spring_assignment.model.SupportSchedule;
import abhinab.full_stack.spring_assignment.model.SupportStatus;

import java.time.LocalDate;
import java.util.List;

public interface SupportScheduleDb extends JpaRepository<SupportSchedule, Integer> {
    SupportSchedule findByDate(LocalDate date);
    List<SupportSchedule> findByStatus(SupportStatus status);
}