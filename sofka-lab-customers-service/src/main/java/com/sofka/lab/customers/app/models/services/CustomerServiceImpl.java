package com.sofka.lab.customers.app.models.services;

import com.sofka.lab.common.exceptions.BusinessLogicException;
import com.sofka.lab.customers.app.models.dao.CustomerDao;
import com.sofka.lab.customers.app.models.entity.CustomerEntity;
import com.sofka.lab.customers.app.models.entity.dtos.CustomerDto;
import com.sofka.lab.customers.app.models.entity.dtos.CustomerMapper;
import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final Logger logger = org.slf4j.LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerDao customerDao;
    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerDao customerDao, PasswordEncoder passwordEncoder, CustomerMapper customerMapper) {
        this.customerDao = customerDao;
        this.passwordEncoder = passwordEncoder;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> findAll() {
        return customerDao.findDtoAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto findById(Long id) {
        Optional<CustomerDto> customerDto = Optional.ofNullable(customerDao.findDtoById(id));
        return customerDto.orElseThrow(() -> new BusinessLogicException(3000));
    }

    @Override
    @Transactional
    public CustomerDto save(CustomerEntity customer) {
        if (customerDao.existsByIdentification(customer.getIdentification())) {
            throw new BusinessLogicException(3001);
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerMapper.toDto(customerDao.save(customer));
    }

    @Override
    @Transactional
    public CustomerDto update(CustomerDto customerDto) {
        var customerSaved = this.customerDao
                .findById(customerDto.getId())
                .map(customerEntity -> {
                    System.out.println("element idenification = " + customerEntity.getIdentification());
                    customerMapper.updateEntityFromDto(customerDto, customerEntity);
                    System.out.println("element kc = " + customerEntity);
                    return customerDao.save(customerEntity);
                })
                .orElseThrow(() -> new BusinessLogicException(3000));
        logger.info("Customer updated: {}", customerSaved.getId());
        return customerDto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var customer = this.customerDao
                .findById(id)
                .orElseThrow(() -> new BusinessLogicException(3001));
        customer.setStatus(false);
        this.customerDao.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto findByIdentification(String identification) {
        return customerDao.findDtoByIdentification(identification);
    }
}
