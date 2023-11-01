package com.fabrick.bussolino.service;

import com.fabrick.bussolino.model.bonifico.AcccountModel;
import com.fabrick.bussolino.response.external.ExternalBonificoResponse;
import com.fabrick.bussolino.utility.LoggerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.fabrick.bussolino.utility.Utility.preCheckField;
import static com.fabrick.bussolino.utility.Utility.prepareHttpHeader;

@Service
public class BonificoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BonificoService.class);
    private final String API_BONIFICO_SERVICE = "https://sandbox.platfr.io//api/gbs/banking/v4.0/accounts/accountID/balance";
    private final RestTemplate restTemplate;

    public BonificoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    public ExternalBonificoResponse moneyTransfer(Long accountId) {
        LOGGER.info("Ricevuta richiesta di recupero saldo per l'account {}", accountId);
        preCheckField("accountId", accountId.toString(), 8);
        HttpEntity<String> entity = new HttpEntity<>("", prepareHttpHeader());
        LoggerUtility.logChiamataServizioEsterno(API_BONIFICO_SERVICE, accountId.toString());
        ResponseEntity<ExternalBonificoResponse> response =  restTemplate.exchange(API_BONIFICO_SERVICE.replace("accountID", accountId.toString()), HttpMethod.POST, entity, new ParameterizedTypeReference<ExternalBonificoResponse>() {
        });
        //TODO SCOMMENTARE LINEA logResponseCode(response.getStatusCode().toString(), response.getBody().getPayload().toString());
        return response.getBody();
    }
}
