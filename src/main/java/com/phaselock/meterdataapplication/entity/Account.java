package com.phaselock.meterdataapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "account_number")
    private Integer accountNumber;
    @Column(name = "balance")
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}
