package ola.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ola_coupon")
public class OlaCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private int id;

    @Column(name = "code",nullable = false)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "discount_percentage")
    private double discountPercentage;

    @Column(name = "valid_from",nullable = false)
    private LocalDateTime validFrom;

    @Column(name = "valid_to",nullable = false)
    private LocalDateTime validTo;

    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

}