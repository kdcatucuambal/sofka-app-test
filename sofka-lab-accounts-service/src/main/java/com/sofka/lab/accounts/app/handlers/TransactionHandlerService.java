package com.sofka.lab.accounts.app.handlers;

import com.sofka.bank.objects.*;

import java.time.LocalDateTime;
import java.util.Date;

public interface TransactionHandlerService {

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
