package com.sofka.lab.accounts.app.models.service;

import com.sofka.bank.objects.Customer;
import com.sofka.lab.accounts.app.clients.CustomerRest;
import com.sofka.lab.accounts.app.clients.CustomerRestAdapter;
import com.sofka.lab.accounts.app.models.dao.MovementDao;
import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.dtos.MovementDto;
import com.sofka.lab.accounts.app.models.entity.MovementEntity;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import com.sofka.lab.common.dtos.CustomerDto;
import com.sofka.lab.common.dtos.AccountReportDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Service
public class MovementServiceImpl implements MovementService {

    private static final Logger logger = LoggerFactory.getLogger(MovementServiceImpl.class);

    private final MovementDao movementDao;
    private final AccountService accountService;
    private final CustomerRestAdapter customerRest;

    public MovementServiceImpl(MovementDao movementDao, AccountService accountService, CustomerRestAdapter customerRest) {
        this.movementDao = movementDao;
        this.accountService = accountService;
        this.customerRest = customerRest;
    }


    @Override
    public MovementEntity save(MovementEntity movement) {
        String accountNumber = movement.getAccount().getNumber();
        AccountDto accountDto = accountService.findByNumber(accountNumber);
        if (accountDto == null) {
            throw new BusinessLogicException("Cuenta no encontrada para la transacción.", "200");
        }

        BigDecimal balance = accountDto.getAvailableBalance().add(movement.getAmount());
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessLogicException("El saldo es insuficiente para la transacción.", "201");
        }
        //AccountService.updateSaldo(numeroCuenta, saldo);
        movement.setBalance(movement.getBalance());
        movement.setBalance(balance);
        accountDto.setAvailableBalance(balance);
        movement.setAccount(accountDto.toEntity());
        movement.setDate(LocalDateTime.now());
        movement.setType(movement.getAmount().compareTo(BigDecimal.ZERO) > 0 ? "DEPOSITO" : "RETIRO");
        accountService.updateBalance(accountNumber, balance);
        return movementDao.save(movement);
    }


    @Override
    public List<MovementEntity> findAll() {
        return movementDao.findAll();
    }

    @Override
    public List<MovementDto> findByAccountNumber(String accountNumber) {
        return movementDao.findByAccountNumber(accountNumber);
    }

    @Override
    public MovementEntity findById(Long id) {
        return movementDao.findById(id).orElse(null);
    }

    @Override
    public List<AccountReportDto> getAccountReportByCustomerIdentification(String customerId, LocalDateTime startDate, LocalDateTime endDate)  {
        logger.info("startDates=>: {}", startDate);
        logger.info("endDates=>: {}", endDate);
        Customer customerDto = customerRest.findByIdentification(customerId);
        if (customerDto == null) {
            throw new BusinessLogicException("Cliente no encontrado para generar el reporte.", "202");
        }
        List<AccountReportDto> reporteCuentas = movementDao.report(customerDto.getId(), startDate, endDate);
//        List<ReporteCuentasDto> reporteCuentas = MovementDao.report(clienteDto.getId());
        reporteCuentas.forEach(r -> r.setCustomer(customerDto.getName()));
        return reporteCuentas;
    }


}
