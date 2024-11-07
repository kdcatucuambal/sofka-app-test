package com.sofka.lab.accounts.app.transactions.infrastructure.adapter.in.rest.adapter;

import com.sofka.bank.objects.*;

import java.time.LocalDateTime;

public interface TransactionRestAdapter {

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
