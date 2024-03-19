package com.phaselock.meterdataapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "apartment")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"apartmentAndOwners"})
@EqualsAndHashCode(exclude = {"apartmentAndOwners"})
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "apartment_number")
    private Integer apartmentNumber;
    @Column(name = "area")
    private BigDecimal area;
    @ManyToOne
    private House house;
    @Builder.Default
    @OneToMany(mappedBy = "apartment")
    private List<ApartmentAndOwner> apartmentAndOwners = new ArrayList<>();
}
