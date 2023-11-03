package com.fabrick.bussolino.service;

import com.fabrick.bussolino.request.bonifico.external.ExternalBonificoRequest;
import com.fabrick.bussolino.request.bonifico.internal.InternalBonificoRequest;
import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.response.bonifico.external.ExternalBonificoResponse;
import com.fabrick.bussolino.utility.LocalDateAdapter;
import com.fabrick.bussolino.utility.LoggerUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.fabrick.bussolino.utility.LoggerUtility.logChiamataServizioEsterno;
import static com.fabrick.bussolino.utility.Utility.*;

@Service
public class BonificoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BonificoService.class);
    private final String API_BONIFICO_SERVICE = "https://sandbox.platfr.io//api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers";
    private final RestTemplate restTemplate;

    public BonificoService() {
        this.restTemplate = new RestTemplate();
    }

    public BonificoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public JsonResponse<ExternalBonificoResponse> moneyTransfer(InternalBonificoRequest internalBonificoRequest) throws IllegalArgumentException {
        LOGGER.info("Ricevuta richiesta di recupero saldo per l'account {}", internalBonificoRequest.getAccountId());
        preCheckBonifico(internalBonificoRequest);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).serializeNulls()
                .create();
        ExternalBonificoRequest externalBonificoRequest = mockingTryCallExtended(internalBonificoRequest);
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(externalBonificoRequest), prepareHttpHeader());
        String url = UriComponentsBuilder.fromHttpUrl(API_BONIFICO_SERVICE)
                .buildAndExpand(internalBonificoRequest.getAccountId().toString())
                .toUriString();
        logChiamataServizioEsterno(url, internalBonificoRequest.getAccountId().toString(), entity);
        ResponseEntity<JsonResponse<ExternalBonificoResponse>> response = null;
        try {

            restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {
            });
        } catch (HttpStatusCodeException ex) {
            LOGGER.error("Errore nella chiamata al servizio esterno.");
            List<String> listEx = new ArrayList<>();
            listEx.add(ex.getResponseBodyAsString());
            response = new ResponseEntity<>(new JsonResponse<>(HttpStatus.BAD_REQUEST, listEx, new ExternalBonificoResponse()), HttpStatus.BAD_REQUEST);
        } finally {
            assert response != null;
            LoggerUtility.logJsonResponse(Objects.requireNonNull(response.getBody()));
        }
        return response.getBody();
    }


    private void preCheckBonifico(InternalBonificoRequest internalBonificoRequest) throws IllegalArgumentException {
        preCheckField("accountId", internalBonificoRequest.getAccountId().toString(), 8, true);
        preCheckField("receiverName", internalBonificoRequest.getReceiverName(), -1, false);
        preCheckField("description", internalBonificoRequest.getDescription(), -1, false);
        preCheckField("currency", internalBonificoRequest.getCurrency(), -1, false);
        preCheckField("amount", internalBonificoRequest.getAmount(), -1, false);
        preCheckField("amount", internalBonificoRequest.getExecutionDate(), -1, false);

    }
}
