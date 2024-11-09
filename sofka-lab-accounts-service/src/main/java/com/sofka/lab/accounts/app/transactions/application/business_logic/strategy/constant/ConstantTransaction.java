package com.sofka.lab.accounts.app.transactions.application.business_logic.strategy.constant;

import java.math.BigDecimal;

public class ConstantTransaction {

    public static String DEBIT_TYPE = "DBT";
    public static String CREDIT_TYPE = "CRE";

    public static BigDecimal MIN_AMOUNT_DBT = new BigDecimal(1);
    public static BigDecimal MAX_AMOUNT_DBT = new BigDecimal(5000);

    public static BigDecimal MIN_AMOUNT_CRE = new BigDecimal("0.5");
    public static BigDecimal MAX_AMOUNT_CRE = new BigDecimal(10000);
}
