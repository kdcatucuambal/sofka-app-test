package com.sofka.lab.accounts.app.transactions.application.business_logic;

import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;

public interface BLFindByIdTransaction {

    TransactionModel find(Long id);

}