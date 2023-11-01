package com.fabrick.bussolino.service;

import com.fabrick.bussolino.request.ExternalBonificoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static com.fabrick.bussolino.utility.Utility.preCheckField;

@Service
public class BonificoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BonificoService.class);
    private final String API_BONIFICO_SERVICE = "https://sandbox.platfr.io//api/gbs/banking/v4.0/accounts/accountID/balance";
    private final RestTemplate restTemplate;

    public BonificoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private HttpHeaders prepareHttpHeader(String accountId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("Auth-Schema", "S2S");
        httpHeaders.add("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        httpHeaders.add("idChiave", "3202");
        return httpHeaders;
    }

    public ExternalBonificoRequest moneyTransfer(String accountId) {
        LOGGER.info("Ricevuta richiesta di recupero saldo per l'account {}", accountId);
        preCheckField("accountId", accountId, 8);
        HttpEntity<String> entity = new HttpEntity<>("", prepareHttpHeader(accountId));
        LOGGER.info("Avvio dhiamata per recupero saldo, al servizio esterno {} per l'account {}", API_BONIFICO_SERVICE, accountId);
        return restTemplate.exchange(API_BONIFICO_SERVICE.replace("accountID", accountId), HttpMethod.POST, entity, new ParameterizedTypeReference<ExternalBonificoRequest>() {
        }).getBody();
    }
}
