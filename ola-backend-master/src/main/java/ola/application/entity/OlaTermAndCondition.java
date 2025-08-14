package ola.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ola_term_and_condition")
public class OlaTermAndCondition {

    @Id
    @Column(name = "t_c_id")
    private int id;

    // Variables representing different sections of the terms and conditions
    @Column(name = "general_terms")
    private String generalTerms;

    @Column(name = "booking_and_reservations")
    private String bookingAndReservations;

    @Column(name = "passenger_responsibilities")
    private String passengerResponsibilities;

    @Column(name = "driver_responsibilities")
    private String driverResponsibilities;

    @Column(name = "fares_and_payments")
    private String faresAndPayments;

    @Column(name = "cancellations_and_refunds")
    private String cancellationsAndRefunds;

    @Column(name = "liability")
    private String liability;

    @Column(name = "complaints_and_disputes")
    private String complaintsAndDisputes;

    @Column(name = "amendments")
    private String amendments;

    @Column(name = "privacy_policy")
    private String privacyPolicy;

    @Column(name = "contact_information")
    private String contactInformation;

    // Metadata
    @Column(name = "effective_date")
    private LocalDateTime effectiveDate;

    @Column(name = "version")
    private String version;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
