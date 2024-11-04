package com.sofka.lab.accounts.app.models.entities;



import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_movements")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mov_id", nullable = false)
    private Long id;

    @Column(name = "mov_type", nullable = false, length = 3)
    private String type;


    @Column(name = "mov_amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "mov_balance", nullable = false)
    private BigDecimal balance;


    @Column(name = "mov_date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "acc_id", nullable = false)
    private AccountEntity account;



}
