package com.phaselock.meterdataapplication.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"apartment"})
@EqualsAndHashCode(exclude = {"apartment"})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "account_number")
    private Integer accountNumber;
    @OneToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}
