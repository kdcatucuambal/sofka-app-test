package com.sofka.lab.accounts.app.controllers;

import com.sofka.bank.objects.*;
import com.sofka.lab.accounts.app.controllers.adapters.transactions.TransactionServiceAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;



@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionServiceAdapter transactionService;

    public TransactionController(TransactionServiceAdapter transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionPSTRs transactionPST(@RequestBody TransactionPSTRq transactionPSTRq) {
        return this.transactionService.execTransactionPST(transactionPSTRq);
    }

    @GetMapping
    public TransactionGETAllRs transactionGETAll() {
        return this.transactionService.execTransactionGETAll();
    }

    @GetMapping("/{id}")
    public TransactionGETByCodeRs transactionGETByCode(@PathVariable Long id) {
        return this.transactionService.execTransactionGETByCode(id);
    }

    @GetMapping("/by-account-number/{accountNumber}")
    public TransactionGETByAccountNumberRs transactionGETByAccountNumber(@PathVariable String accountNumber) {
        return this.transactionService.execTransactionGETByAccountNumber(accountNumber);
    }


    @GetMapping("/report/{customerIdentification}")
    public TransactionReportGETByCustomerIdentificationRs
    accountReport(@PathVariable String customerIdentification,
                  @RequestParam LocalDateTime startDate,
                  @RequestParam LocalDateTime endDate) {
        return this.transactionService
                .execTransReportGetByCustomerIdentification(customerIdentification, startDate, endDate);
    }

}
