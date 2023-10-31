package com.axerve.bussolino.service;

import com.axerve.bussolino.ApplicationMain;
import com.axerve.bussolino.response.external.ExternalSaldoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static com.axerve.bussolino.utility.Utility.preCheckField;

@Service
public class SaldoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaldoService.class);
    private final String API_ACCOUNT_SERVICE = "https://sandbox.platfr.io//api/gbs/banking/v4.0/accounts/accountID/balance";
    private final RestTemplate restTemplate;

    public SaldoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExternalSaldoResponse getAccount(String accountId) throws RestClientException, IllegalArgumentException {
        HttpEntity<String> entity = new HttpEntity<>("", prepareHttpHeader(accountId));
        LOGGER.info("Avvio Chiamata al servizio esterno {} per l'account {}", API_ACCOUNT_SERVICE, accountId);
        ResponseEntity<ExternalSaldoResponse> response;
        preCheckField("accountId",accountId, 8);
        response = restTemplate.exchange(API_ACCOUNT_SERVICE.replace("accountID", accountId), HttpMethod.GET, entity, new ParameterizedTypeReference<ExternalSaldoResponse>() {
        });
        return response.getBody();
    }


    private HttpHeaders prepareHttpHeader(String accountId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("Auth-Schema", "S2S");
        httpHeaders.add("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        httpHeaders.add("idChiave", "3202");
        return httpHeaders;
    }
}
