package abhinab.full_stack.spring_assignment.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupportSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "support_id", unique = true)
    private int id;
    @Column(unique = true)
    private LocalDate date;
    private DayOfWeek dayOfWeek;
    @JsonProperty(value = "start-dti")
    private LocalDateTime startDTi;
    @JsonProperty(value = "end-dti")
    private LocalDateTime endDTi;
    private Boolean isSpecialDay;
    private long phNumber;
    @Nullable
    @Column(name = "reference_number", unique = true)
    private String refNo;
    private SupportStatus status;

    @PrePersist
    public void generateRefNo() {
        if (this.refNo == null || this.refNo.isEmpty()) {
            this.refNo = "REF-000" + this.id;
            this.status = SupportStatus.NOT_RESOLVED;
        }
    }
}
