package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.entity;


import com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.entity.TransactionEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_accounts")
public class AccountEntity {

    @Id
    @Column(name = "acc_id", nullable = false)
    private Long id;

    @Column(name = "acc_number", nullable = false, unique = true, length = 15)
    private String number;

    @Column(name = "acc_type", nullable = false, length = 3)
    private String type;

    @Column(name = "acc_init_balance", nullable = false)
    private BigDecimal initBalance;

    @Column(name = "acc_balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "acc_state", nullable = false)
    private Boolean status;

    @Column(name = "cli_id", nullable = false)
    private Long customerId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<TransactionEntity> movements;


}
