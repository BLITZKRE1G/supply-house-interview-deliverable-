package abhinab.full_stack.spring_assignment.mapper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.stereotype.Service;

import abhinab.full_stack.spring_assignment.model.Meridiam;
import abhinab.full_stack.spring_assignment.model.SupportSchedule;
import abhinab.full_stack.spring_assignment.model.SupportScheduleDTO;

@Service
public class SupportScheduleMapper {
    private final DateTimeFormatter formatter12Hour = DateTimeFormatter.ofPattern("hh:mm a");
    public static final Map<DayOfWeek, SupportSchedule> defaultSchedule = Map.of(
            DayOfWeek.MONDAY, new SupportSchedule(1, LocalDate.now(),
                    DayOfWeek.MONDAY, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)),
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 45)), false, 888_551_7600L, null, null),
            DayOfWeek.TUESDAY, new SupportSchedule(2, LocalDate.now(),
                    DayOfWeek.TUESDAY, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)),
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 45)), false, 888_551_7600L, null, null),
            DayOfWeek.WEDNESDAY, new SupportSchedule(3, LocalDate.now(),
                    DayOfWeek.WEDNESDAY, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)),
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 45)), false, 888_551_7600L, null, null),
            DayOfWeek.THURSDAY, new SupportSchedule(4, LocalDate.now(),
                    DayOfWeek.THURSDAY, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)),
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 45)), false, 888_551_7600L, null, null),

            DayOfWeek.FRIDAY, new SupportSchedule(5, LocalDate.now(),
                    DayOfWeek.FRIDAY, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)),
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 45)), false, 888_551_7600L, null, null),
            DayOfWeek.SATURDAY, new SupportSchedule(6, LocalDate.now(),
                    DayOfWeek.SATURDAY, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)),
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 45)), false, 888_551_7600L, null, null),
            DayOfWeek.SUNDAY, new SupportSchedule(7, LocalDate.now(),
                    DayOfWeek.SUNDAY, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)),
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 45)), false, 888_551_7600L, null, null));

    public SupportScheduleDTO toDto(SupportSchedule schedule) {
        SupportSchedule monSchedule = defaultSchedule.get(DayOfWeek.MONDAY);
        SupportSchedule friSchedule = defaultSchedule.get(DayOfWeek.FRIDAY);
        SupportSchedule satSchedule = defaultSchedule.get(DayOfWeek.SATURDAY);
        LocalTime time = LocalTime.parse(schedule.getEndDTi().toLocalTime().toString());

        String schedule12HrEnd = time.format(formatter12Hour).split(" ")[0];
        String monday12HrEnd = LocalTime.parse(monSchedule.getEndDTi().toLocalTime().toString()).format(formatter12Hour)
                .split(" ")[0];
        String fri12HrEnd = LocalTime.parse(friSchedule.getEndDTi().toLocalTime().toString()).format(formatter12Hour)
                .split(" ")[0];
        String sat12HrEnd = LocalTime.parse(satSchedule.getEndDTi().toLocalTime().toString()).format(formatter12Hour)
                .split(" ")[0];
                System.out.println(monSchedule.getStartDTi().getHour());

        SupportScheduleDTO dto = new SupportScheduleDTO(
                schedule.getStartDTi().toLocalDate(),
                schedule.getDayOfWeek(),
                schedule.getIsSpecialDay(),
                schedule.getStartDTi().getHour(),
                schedule.getStartDTi().getMinute(),
                schedule.getStartDTi().getHour() >= 12 ? Meridiam.PM : Meridiam.AM,
                Integer.parseInt(schedule12HrEnd.split(":")[0]),
                Integer.parseInt(schedule12HrEnd.split(":")[1]),
                schedule.getEndDTi().getHour() >= 12 ? Meridiam.PM : Meridiam.AM,
                schedule.getPhNumber(), 
                schedule.getRefNo(),
                
                // monday-schedule
                monSchedule.getStartDTi().getHour(),
                monSchedule.getStartDTi().getHour() >= 12 ? Meridiam.PM : Meridiam.AM,
                Integer.parseInt(monday12HrEnd.split(":")[0]),
                Integer.parseInt(monday12HrEnd.split(":")[1]),
                monSchedule.getEndDTi().getHour() >= 12 ? Meridiam.PM : Meridiam.AM,

                // friday
                friSchedule.getStartDTi().getHour(),
                friSchedule.getStartDTi().getHour() >= 12 ? Meridiam.PM : Meridiam.AM,
                Integer.parseInt(fri12HrEnd.split(":")[0]),
                Integer.parseInt(fri12HrEnd.split(":")[1]),
                friSchedule.getEndDTi().getHour() >= 12 ? Meridiam.PM : Meridiam.AM,

                // Saturday-Sunday
                satSchedule.getStartDTi().getHour(),
                satSchedule.getStartDTi().getHour() >= 12 ? Meridiam.PM : Meridiam.AM,
                Integer.parseInt(fri12HrEnd.split(":")[0]),
                Integer.parseInt(sat12HrEnd.split(":")[1]),
                satSchedule.getEndDTi().getHour() >= 12 ? Meridiam.PM : Meridiam.AM
                );
        return dto;
    }
}