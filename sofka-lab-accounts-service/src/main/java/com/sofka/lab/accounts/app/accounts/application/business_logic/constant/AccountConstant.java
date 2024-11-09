package com.sofka.lab.accounts.app.accounts.application.business_logic.constant;

public class AccountConstant {

    public static String CHECKING_ACCOUNT_TYPE = "CTE";
    public static String SAVING_ACCOUNT_TYPE = "AHO";

    public static String fillWithZeros(String number, int length) {
        return String.format("%0" + length + "d", Integer.parseInt(number));
    }

}
