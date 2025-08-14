package ola.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ola_banking_details")
public class OlaBankingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banking_details_id")
    private int id;

    @OneToOne(mappedBy = "bankingDetails")
    private OlaDriver driver;

    @Column(name = "Account_holder_name",nullable = false)
    private String accountHolderName;

    @Column(name = "bank_name",nullable = false)
    private String bankName;

    @Column(name = "branch_name",nullable = false)
    private String branchName;

    @Column(name = "ifsc_code",nullable = false)
    private String ifscCode;

    @Column(name = "upi_id",nullable = false)
    private String upiId;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

}