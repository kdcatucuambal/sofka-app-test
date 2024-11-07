package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.in.rest.mapper;

import com.sofka.bank.objects.Account;
import com.sofka.bank.objects.Customer;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountRestMapper {

    @Mapping(target = "customerId", expression = "java(getCustomerId(account.getCustomer()))")
    @Mapping(target = "type", expression = "java(fromTypeAccount(account.getType()))")
    AccountDomain toAccountDomain(Account account);

    @Mapping(target ="type", expression = "java(toTypeAccount(accountDomain.getType()))")
    @Mapping(target = "customer", expression = "java(getCustomerId(accountDomain.getCustomerId()))")
    Account toAccount(AccountDomain accountDomain);


    default String fromTypeAccount(Account.TypeEnum type) {
        return type.getValue();
    }

    default Account.TypeEnum toTypeAccount(String type) {
        return Account.TypeEnum.fromValue(type);
    }

    default Customer getCustomerId(Long customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
        return customer;
    }

    default Long getCustomerId(Customer customer) {
        if (customer != null) {
            return customer.getId();
        }
        return null;
    }



}
