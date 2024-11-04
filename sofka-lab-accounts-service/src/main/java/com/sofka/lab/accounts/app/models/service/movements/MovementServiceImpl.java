package com.sofka.lab.accounts.app.models.service.movements;

import com.sofka.bank.objects.Customer;
import com.sofka.lab.accounts.app.clients.CustomerRestAdapter;
import com.sofka.lab.accounts.app.models.dao.MovementDao;
import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.dtos.TransactionDto;
import com.sofka.lab.accounts.app.models.entities.AccountEntity;
import com.sofka.lab.accounts.app.models.entities.TransactionEntity;
import com.sofka.lab.accounts.app.models.service.accounts.AccountService;
import com.sofka.lab.accounts.app.models.service.movements.mappers.TransactionMapper;
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

    public MovementServiceImpl(MovementDao movementDao,
                               AccountService accountService,
                               CustomerRestAdapter customerRest,
                               TransactionMapper transactionMapper,
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
    public TransactionEntity save(TransactionEntity movement) {
        if (movement.getAmount() != null
                && movement.getAmount().compareTo(BigDecimal.valueOf(0.0)) < 0) throw new BusinessLogicException(2002);
        var accountDto = getAccount(movement.getAccount());
        if (accountDto == null) throw new BusinessLogicException(2000);
        var strategy = trnStrategies.get(movement.getType());
        var movementEntity = strategy.process(movement, accountDto);
        accountService.updateBalance(accountDto.getNumber(), accountDto.getAvailableBalance());
        return movementDao.save(movementEntity);
    }


    @Override
    @Transactional(readOnly = true)
    public List<TransactionEntity> findAll() {
        return movementDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionDto> findByAccountNumber(String accountNumber) {
        return movementDao.findByAccountNumber(accountNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionEntity findById(Long id) {
        return movementDao.findById(id).orElseThrow(() -> new BusinessLogicException(2003));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountReportDto> getAccountReportByCustomerIdentification(
            String customerId, LocalDateTime startDate, LocalDateTime endDate) {
        Customer customerDto = customerRest.findByIdentification(customerId);
        if (customerDto == null) throw new BusinessLogicException(2002);
        List<AccountReportDto> reporteCuentas = movementDao.report(customerDto.getId(), startDate, endDate);
        reporteCuentas.forEach(r -> r.setCustomer(customerDto.getName()));
        return reporteCuentas;
    }

    private AccountDto getAccount(AccountEntity accountEntity) {
        return accountEntity.getNumber() != null ?
                accountService.findByNumber(accountEntity.getNumber()) :
                accountService.findById(accountEntity.getId());
    }


}
