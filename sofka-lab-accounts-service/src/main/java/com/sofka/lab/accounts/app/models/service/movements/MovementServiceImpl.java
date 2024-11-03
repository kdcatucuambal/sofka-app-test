package com.sofka.lab.accounts.app.models.service.movements;

import com.sofka.bank.objects.Customer;
import com.sofka.lab.accounts.app.clients.CustomerRestAdapter;
import com.sofka.lab.accounts.app.models.dao.MovementDao;
import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.dtos.MovementDto;
import com.sofka.lab.accounts.app.models.entity.MovementEntity;
import com.sofka.lab.accounts.app.models.service.accounts.AccountService;
import com.sofka.lab.accounts.app.models.service.movements.strategies.TransactionStrategy;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import com.sofka.lab.common.dtos.AccountReportDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovementServiceImpl implements MovementService {

    private static final Logger logger = LoggerFactory.getLogger(MovementServiceImpl.class);

    private final MovementDao movementDao;
    private final AccountService accountService;
    private final CustomerRestAdapter customerRest;

    private final Map<String, TransactionStrategy> trnStrategies;

    public MovementServiceImpl(MovementDao movementDao, AccountService accountService, CustomerRestAdapter customerRest,
                               List<TransactionStrategy> strategyList) {
        this.movementDao = movementDao;
        this.accountService = accountService;
        this.customerRest = customerRest;
        this.trnStrategies = strategyList.stream()
                .collect(Collectors.toMap(
                        TransactionStrategy::getType,
                        strategy -> strategy
                ));
    }


    @Override
    @Transactional
    public MovementEntity save(MovementEntity movement) {

        if (movement.getAmount() != null && movement.getAmount().compareTo(BigDecimal.valueOf(0.0)) < 0) {
            throw new BusinessLogicException(2002);
        }
        String accountNumber = movement.getAccount().getNumber();
        AccountDto accountDto = null;
        if (accountNumber != null) {
            accountDto = accountService.findByNumber(accountNumber);
        } else {
            accountDto = accountService.findById(movement.getAccount().getId());
        }

        if (accountDto == null) {
            throw new BusinessLogicException(2000);
        }

        TransactionStrategy strategy = trnStrategies.get(movement.getType());
        MovementEntity movementEntity = strategy.process(movement, accountDto);
        accountService.updateBalance(accountNumber, accountDto.getAvailableBalance());
        return movementDao.save(movementEntity);
    }


    @Override
    @Transactional(readOnly = true)
    public List<MovementEntity> findAll() {
        return movementDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementDto> findByAccountNumber(String accountNumber) {
        return movementDao.findByAccountNumber(accountNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public MovementEntity findById(Long id) {
        return movementDao.findById(id).orElseThrow(() -> new BusinessLogicException(2003));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountReportDto> getAccountReportByCustomerIdentification(
            String customerId, LocalDateTime startDate, LocalDateTime endDate) {
        Customer customerDto = customerRest.findByIdentification(customerId);
        if (customerDto == null) {
            throw new BusinessLogicException(2002);
        }
        List<AccountReportDto> reporteCuentas = movementDao.report(customerDto.getId(), startDate, endDate);
        reporteCuentas.forEach(r -> r.setCustomer(customerDto.getName()));
        return reporteCuentas;
    }


}
