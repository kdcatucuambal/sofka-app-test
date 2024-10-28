package com.sofka.lab.accounts.app.controllers;

import com.sofka.lab.accounts.app.models.dtos.MovementDto;
import com.sofka.lab.accounts.app.models.entity.MovementEntity;
import com.sofka.lab.accounts.app.models.service.MovementService;
import com.sofka.lab.common.dtos.AccountReportDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/movements")
public class MovementController {
    private final MovementService movementService;
    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovementEntity save(@RequestBody MovementEntity movimiento) {
        return movementService.save(movimiento);
    }

    @GetMapping
    public List<MovementEntity> findAll() {
        return movementService.findAll();
    }

    @GetMapping("/{id}")
    public MovementEntity findById(@PathVariable Long id) {
        return movementService.findById(id);
    }

    @GetMapping("/by-account-number/{accountNumber}")
    public List<MovementDto> findByAccountNumber(@PathVariable String accountNumber) {
        return movementService.findByAccountNumber(accountNumber);
    }


    @GetMapping("/report/{customerIdentification}")
    public List<AccountReportDto> accountReport(@PathVariable String customerIdentification,
                                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        return movementService.getAccountReportByCustomerIdentification(customerIdentification, startDate, endDate);

    }


}
