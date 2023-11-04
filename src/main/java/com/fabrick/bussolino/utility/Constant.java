package com.fabrick.bussolino.utility;

public class Constant {


    //ENDPOINT
    public static final String BASE_URL_REQUEST_MAPPING = "/api";
    public static final String API_LETTURA_SALDO = "/letturaSaldo";
    public static final String API_BONIFICO = "/bonifico";
    public static final String API_LISTA_TRANSAZIONI = "/letturaTransazioni";

    //LINK ESTERNI
    public  static final String BASE_EXTERNAL_URL ="https://sandbox.platfr.io";
    public static final String API_BONIFICO_SERVICE = BASE_EXTERNAL_URL +"/api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers";
    public static final String API_GET_SALDO_SERVICE = BASE_EXTERNAL_URL +"/api/gbs/banking/v4.0/accounts/{accountId}/balance";
    public static final String API_TRANSAZIONI_SERVICE = BASE_EXTERNAL_URL +"/api/gbs/banking/v4.0/accounts/{accountId}/transactions?fromAccountingDate={fromAccountingDate}&toAccountingDate={toAccountingDate}";


}
