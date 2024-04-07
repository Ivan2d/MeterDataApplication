package com.phaselock.meter_data_application.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@Table(name = "apartment_owner")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"apartment", "owner"})
@EqualsAndHashCode(exclude = {"apartment", "owner"})
public class ApartmentAndOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public void setOwner(Owner owner) {
        this.owner = owner;
        this.owner.getApartmentAndOwners().add(this);
    }
    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
        this.apartment.getApartmentAndOwners().add(this);
    }

}
