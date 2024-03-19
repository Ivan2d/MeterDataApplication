package com.phaselock.meterdataapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meter_reading")
@ToString(exclude = {"meter"})
@EqualsAndHashCode(exclude = {"meter"})
public class MeterReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "reading")
    private BigDecimal reading;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "meter_id")
    private Meter meter;
}