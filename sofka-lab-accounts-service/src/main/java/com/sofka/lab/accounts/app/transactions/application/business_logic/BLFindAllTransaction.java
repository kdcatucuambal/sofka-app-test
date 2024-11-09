package com.sofka.lab.accounts.app.transactions.application.business_logic;

import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;

import java.util.List;

public interface BLFindAllTransaction {

    List<TransactionModel> findAll();

}
