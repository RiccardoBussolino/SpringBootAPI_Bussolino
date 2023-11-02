package com.fabrick.bussolino.service;

import com.fabrick.bussolino.response.external.ExternalSaldoResponse;
import com.fabrick.bussolino.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static com.fabrick.bussolino.utility.LoggerUtility.logChiamataServizioEsterno;
import static com.fabrick.bussolino.utility.LoggerUtility.logResponseCode;
import static com.fabrick.bussolino.utility.Utility.prepareHttpHeader;

@Service
public class SaldoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaldoService.class);
    private final String API_GET_SALDO_SERVICE = "https://sandbox.platfr.io//api/gbs/banking/v4.0/accounts/{accountId}/balance";
    private final RestTemplate restTemplate;

    public SaldoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExternalSaldoResponse getSaldo(Long accountId) throws RestClientException, IllegalArgumentException {

        LOGGER.info("Ricevuta richiesta di recupero saldo per l'account {}", accountId);
        Utility.preCheckField("accountId", String.valueOf(accountId), 8,true);
        HttpEntity<String> entity = new HttpEntity<>("", prepareHttpHeader());
        logChiamataServizioEsterno(API_GET_SALDO_SERVICE, String.valueOf(accountId),entity.getHeaders().toString(), entity.getBody());
        ResponseEntity<ExternalSaldoResponse> response = restTemplate.exchange(API_GET_SALDO_SERVICE.replace("{accountId}", accountId.toString()), HttpMethod.GET, entity, new ParameterizedTypeReference<ExternalSaldoResponse>() {});
        logResponseCode(response.getStatusCode().toString(), Objects.requireNonNull(response.getBody()).getError().toString(),Objects.requireNonNull(response.getBody()).getPayload().toString());
        return response.getBody();
    }

}
