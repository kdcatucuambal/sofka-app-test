package com.sofka.lab.accounts.app.controllers.adapters.validators;

import com.sofka.bank.objects.Account;
import com.sofka.bank.objects.AccountPSTRq;
import com.sofka.bank.objects.AccountPTCRq;
import com.sofka.bank.objects.CustomerPSTRq;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.IllegalFormatCodePointException;


public interface AccountValidator extends Validator {


//    @Override
//    public boolean supports(Class<?> clazz) {
//        return
//                AccountPTCRq.class.isAssignableFrom(clazz) ||
//                        AccountPSTRq.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        System.out.println("AccountValidator.validate started");
//        Account account = null;
//
//        if (target instanceof AccountPTCRq) {
//            account = ((AccountPTCRq) target).getAccount();
//        } else if (target instanceof AccountPSTRq) {
//            account = ((AccountPSTRq) target).getAccount();
//        }
//
//        if (account == null) {
//            errors.rejectValue("account", "account", "No se ha enviado la información de la cuenta");
//            return;
//        }
//
//
//    }
//
//    private void validateAccountPSTRq(Account account, Errors errors) {
//        if (account.getCustomer() == null || account.getCustomer().getId() == null ||
//                account.getType() == null || account.getNumber() == null ||
//                account.getStatus() == null ||
//                account.getInitBalance() == null) {
//            errors.rejectValue("account", "account", "Todos los campos son requeridos");
//            return;
//        }
//
//        if (account.getInitBalance().compareTo(BigDecimal.ZERO) < 0) {
//            System.out.println("AccountValidator.validate init balance error");
//            errors.rejectValue("account", "account", "El saldo inicial no puede ser negativo");
//            return;
//        }
//
//        if (account.getNumber().isEmpty() || account.getNumber().length() < 10) {
//            System.out.println("AccountValidator.validate account number error");
//            errors.rejectValue("account", "account", "El número de cuenta debe tener al menos 10 caracteres");
//            return;
//        }
//
//        this.commonValidation(account, errors);
//
//
//
//    }
//
//    private void validateAccountPTCRq(Account account, Errors errors) {
//        if (account.getType() == null || account.getStatus() == null) {
//            errors.rejectValue("account", "account", "Todos los campos son requeridos.");
//            return;
//        }
//        this.commonValidation(account, errors);
//    }
//
//    private void commonValidation(Account account, Errors errors) {
//        try {
//            Account.TypeEnum.valueOf(account.getType().getValue());
//        } catch (IllegalArgumentException e) {
//            errors.rejectValue("account", "account", "Tipo de cuenta no válido.");
//        }
//    }


}

