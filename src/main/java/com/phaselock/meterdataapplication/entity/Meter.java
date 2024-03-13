package com.phaselock.meterdataapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "meter")
@NoArgsConstructor
@AllArgsConstructor
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private MeterType type;

    @Column(name = "reading")
    private BigDecimal reading;

    @Column(name = "installation_date")
    private Date installationDate;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}
