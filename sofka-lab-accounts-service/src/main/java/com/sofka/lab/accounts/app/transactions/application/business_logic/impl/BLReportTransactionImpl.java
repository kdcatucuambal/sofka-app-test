package com.sofka.lab.accounts.app.transactions.application.business_logic.impl;

import com.sofka.lab.accounts.app.transactions.application.business_logic.BLReportTransaction;
import com.sofka.lab.accounts.app.transactions.domain.port.out.repository.TransactionRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BLReportTransactionImpl implements BLReportTransaction {

    private final TransactionRepository transactionRepository;

}
