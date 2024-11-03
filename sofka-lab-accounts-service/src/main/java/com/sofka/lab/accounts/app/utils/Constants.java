package com.sofka.lab.accounts.app.utils;

public class Constants {

    public static final String DEBIT_TEXT = "Retiro de: ";
    public static final String CREDIT_TEXT = "Depósito de: ";
    public static final String ACCOUNT_SEQUENCE = "sofka_accounts.tbl_accounts_acc_id_seq";


    public static String fillWithZeros(String number, int length) {
        return String.format("%0" + length + "d", Integer.parseInt(number));
    }

}
