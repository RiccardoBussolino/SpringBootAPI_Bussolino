package com.fabrick.bussolino.dto;

import org.springframework.data.annotation.Id;

import java.sql.Date;

public record TransactionDTO(@Id Integer id, String accountId, String IBAN, String abiCode, String cabCode,
                             String countryCode, String internationalCin, String nationalCin, String account,
                             String productName, Date activatedDate, String currency) {
}
