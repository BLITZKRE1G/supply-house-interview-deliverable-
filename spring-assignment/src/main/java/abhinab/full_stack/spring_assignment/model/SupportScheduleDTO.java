package abhinab.full_stack.spring_assignment.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SupportScheduleDTO(
                @JsonProperty(value = "for-date") LocalDate date,
                @JsonProperty(value = "day") DayOfWeek day,
                @JsonProperty(value = "special-day") Boolean specialDay,
                @JsonProperty(value = "start-hour") int startHour,
                @JsonProperty(value = "start-min") int startMin,
                @JsonProperty(value = "start-meridiam") Meridiam startMeridiam,
                @JsonProperty(value = "end-hour") int endHour,
                @JsonProperty(value = "end-min") int endMin,
                @JsonProperty(value = "end-meridiam") Meridiam endMeridiam,
                @JsonProperty(value = "phone-number") long phNumber,
                @JsonProperty(value = "ref-no") String refNo,

                @JsonProperty(value = "mon-thr-st-hr") int monThrStHr,
                @JsonProperty(value = "mon-thr-st-mer") Meridiam monThrStMer,
                @JsonProperty(value = "mon-thr-end-hr") int monThrEndHr,
                @JsonProperty(value = "mon-thr-end-min") int monThrEndMin,
                @JsonProperty(value = "mon-thr-end-mer") Meridiam monThrEndMer,

                @JsonProperty(value = "fri-st-hr") int friStHr,
                @JsonProperty(value = "fri-st-mer") Meridiam friStMer,
                @JsonProperty(value = "fri-end-hr") int friEndHr,
                @JsonProperty(value = "fri-end-min") int friEndMin,
                @JsonProperty(value = "fri-end-mer") Meridiam friEndMer,
                
                @JsonProperty(value = "sat-sun-st-hr") int satSunStHr,
                @JsonProperty(value = "sat-sun-st-mer") Meridiam satSunStMer,
                @JsonProperty(value = "sat-sun-end-hr") int satSunEndHr,
                @JsonProperty(value = "sat-sun-end-min") int satSunEndMin,
                @JsonProperty(value = "sat-sun-end-mer") Meridiam satSunEndMer
                ) {
}
