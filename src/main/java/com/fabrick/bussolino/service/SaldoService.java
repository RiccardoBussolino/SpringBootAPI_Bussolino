package com.fabrick.bussolino.service;

import com.fabrick.bussolino.response.saldo.external.ExternalSaldoResponse;
import com.fabrick.bussolino.response.JsonResponse;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

import static com.fabrick.bussolino.utility.LoggerUtility.logChiamataServizioEsterno;
import static com.fabrick.bussolino.utility.LoggerUtility.logJsonResponse;
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
        String url=UriComponentsBuilder.fromHttpUrl(API_GET_SALDO_SERVICE)
                .buildAndExpand(accountId)
                .toUriString();
        logChiamataServizioEsterno(url, String.valueOf(accountId),entity);
        ResponseEntity<JsonResponse<ExternalSaldoResponse>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });
        logJsonResponse(Objects.requireNonNull(response.getBody()));
        return response.getBody().getPayload();
    }

}
