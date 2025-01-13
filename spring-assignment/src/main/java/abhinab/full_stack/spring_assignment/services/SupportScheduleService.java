package abhinab.full_stack.spring_assignment.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import abhinab.full_stack.spring_assignment.db.SupportScheduleDb;
import abhinab.full_stack.spring_assignment.mapper.SupportScheduleMapper;
import abhinab.full_stack.spring_assignment.model.SupportSchedule;
import abhinab.full_stack.spring_assignment.model.SupportScheduleDTO;
import abhinab.full_stack.spring_assignment.model.SupportStatus;

@Service
public class SupportScheduleService {
    private final SupportScheduleDb db;
    private static final SupportScheduleMapper mapper = new SupportScheduleMapper();

    public SupportScheduleService(SupportScheduleDb db) {
        this.db = db;
    }

    public SupportScheduleDTO login() {
        LocalDateTime current = LocalDateTime.now();
        SupportSchedule schedule = db.findByDate(LocalDate.now());
        if (schedule == null)
            schedule = SupportScheduleMapper.defaultSchedule.get(current.getDayOfWeek());
        return mapper.toDto(schedule);
    }

    public SupportScheduleDTO getSupportTime(LocalDate date) {
        SupportSchedule schedule = db.findByDate(date);
        System.out.println("found: " + schedule);
        if (schedule != null && schedule.getIsSpecialDay())
            return mapper.toDto(schedule);
        return mapper.toDto(SupportScheduleMapper.defaultSchedule.get(date.getDayOfWeek()));
    }

    @Transactional
    public SupportScheduleDTO createSchedule(SupportSchedule schedule) {
        // schedule.setStartDTi(LocalDateTime.of(schedule.getDate().getYear(),
        // schedule.getDate().getMonth(), schedule.getDate().getDayOfMonth(),
        // schedule.getS, 0, 0));
        schedule.setDayOfWeek(schedule.getDate().getDayOfWeek());
        schedule.setIsSpecialDay(true);
        SupportSchedule savedSchedule = db.save(schedule);
        System.out.println(savedSchedule);
        SupportScheduleDTO dto = mapper.toDto(savedSchedule);
        return dto;
    }

    @Transactional
    public SupportScheduleDTO updateSchedule(SupportSchedule schedule) {
        SupportSchedule savedSchedule = db.findByDate(schedule.getDate());
        System.out.println(savedSchedule);
        if (savedSchedule != null) {
            savedSchedule = db.save(savedSchedule);
            return mapper.toDto(savedSchedule);
        }
        return mapper.toDto(savedSchedule);
    }

    public SupportScheduleDTO getByDay(DayOfWeek day) {
        return mapper.toDto(SupportScheduleMapper.defaultSchedule.get(day));
    }

    @Transactional
    public SupportScheduleDTO resolveQuery(SupportSchedule schedule) {
        if (db.findById(schedule.getId()).isPresent()) {
            schedule.setStatus(SupportStatus.RESOLVED);
            return mapper.toDto(db.save(schedule));
        }
        throw new RuntimeException("cannot find older schedule to mark 'RESOLVED'");
    }

    public List<SupportScheduleDTO> getAllPrioritySchedules() {
        return db.findByStatus(SupportStatus.NOT_RESOLVED)
                .stream()
                .map(schedule -> mapper.toDto(schedule))
                .toList();
    }
}
