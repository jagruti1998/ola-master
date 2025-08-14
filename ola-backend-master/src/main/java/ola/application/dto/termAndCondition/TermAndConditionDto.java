package ola.application.dto.termAndCondition;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class TermAndConditionDto {
    private int id;
    private String generalTerms;
    private String bookingAndReservations;
    private String passengerResponsibilities;
    private String driverResponsibilities;
    private String faresAndPayments;
    private String cancellationsAndRefunds;
    private String liability;
    private String complaintsAndDisputes;
    private String amendments;
    private String privacyPolicy;
    private String contactInformation;
    private LocalDateTime effectiveDate;
    private String version;
    private LocalDateTime lastUpdated;
}
