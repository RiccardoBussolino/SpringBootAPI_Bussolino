package com.fabrick.bussolino.service;

import com.fabrick.bussolino.response.transazione.external.ExternalTransazioneResponse;
import com.fabrick.bussolino.request.transazione.internal.InternalTransazioneRequest;
import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.utility.LoggerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.fabrick.bussolino.utility.LoggerUtility.logChiamataServizioEsterno;
import static com.fabrick.bussolino.utility.Utility.preCheckField;
import static com.fabrick.bussolino.utility.Utility.prepareHttpHeader;

@Service
public class TransazioneService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransazioneService.class);
    private final String API_TRANSAZIONI_SERVICE = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/transactions?fromAccountingDate={fromAccountingDate}&toAccountingDate={toAccountingDate}";
    private final RestTemplate restTemplate;

    public TransazioneService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public JsonResponse<ExternalTransazioneResponse> transactionList(InternalTransazioneRequest internalTransazioneRequest) throws IllegalArgumentException {
        LOGGER.info("Ricevuta richiesta di recupero saldo per l'account {}", internalTransazioneRequest.getAccountId());
        preCheckTransazione(internalTransazioneRequest);
        HttpEntity<String> entity = new HttpEntity<>("", prepareHttpHeader());
        String url = UriComponentsBuilder.fromHttpUrl(API_TRANSAZIONI_SERVICE)
                .buildAndExpand(internalTransazioneRequest.getAccountId(), internalTransazioneRequest.getFromAccountingDate(), internalTransazioneRequest.getToAccountingDate())
                .toUriString();
        logChiamataServizioEsterno(url, internalTransazioneRequest.getAccountId().toString(), entity);
        ResponseEntity<JsonResponse<ExternalTransazioneResponse>> response = null;
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});
        } catch (HttpStatusCodeException ex) {
            LOGGER.error("Errore nella chiamata al servizio esterno.");
            List<String> listEx = new ArrayList<>();
            listEx.add(ex.getResponseBodyAsString());
            response = new ResponseEntity<>(new JsonResponse<>(HttpStatus.BAD_REQUEST, listEx, new ExternalTransazioneResponse()), HttpStatus.BAD_REQUEST);
        } finally {
            assert response != null;
            LoggerUtility.logJsonResponse(Objects.requireNonNull(response.getBody()));
        }
        return response.getBody();
    }

    private void preCheckTransazione(InternalTransazioneRequest internalTransazioneRequest) throws IllegalArgumentException {
        preCheckField("accountId", internalTransazioneRequest.getAccountId().toString(), 8, true);
        preCheckField("fromAccountingDate", internalTransazioneRequest.getFromAccountingDate(), -1, false);
        preCheckField("toAccountingDate", internalTransazioneRequest.getToAccountingDate(), -1, false);
    }
}
