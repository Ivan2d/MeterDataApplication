package com.phaselock.meterdataapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "meter")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"apartment", "meterType"})
@EqualsAndHashCode(exclude = {"apartment", "meterType"})
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "installation_date")
    private LocalDate installationDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private MeterType meterType;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}
