package com.phaselock.meterdataapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "owner")
@ToString(exclude = {"apartmentAndOwners"})
@EqualsAndHashCode(exclude = {"apartmentAndOwners"})
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Builder.Default
    @OneToMany(mappedBy = "owner")
    private List<ApartmentAndOwner> apartmentAndOwners = new ArrayList<>();
}
