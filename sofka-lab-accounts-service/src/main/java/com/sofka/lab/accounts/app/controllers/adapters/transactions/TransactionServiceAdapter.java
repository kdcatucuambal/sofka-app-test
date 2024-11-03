package com.sofka.lab.accounts.app.controllers.adapters.transactions;

import com.sofka.bank.objects.*;

import java.time.LocalDateTime;

public interface TransactionServiceAdapter {

    TransactionGETAllRs execTransactionGETAll();

    TransactionPSTRs execTransactionPST(TransactionPSTRq transactionPSTRs);

    TransactionGETByAccountNumberRs execTransactionGETByAccountNumber(String accountNumber);

    TransactionGETByCodeRs execTransactionGETByCode(Long code);

    TransactionReportGETByCustomerIdentificationRs execTransReportGetByCustomerIdentification(
            String identification,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

}
