package com.fabrick.bussolino.service;

import com.fabrick.bussolino.model.bonifico.AcccountModel;
import com.fabrick.bussolino.model.bonifico.CreditorModel;
import com.fabrick.bussolino.model.bonifico.LegalPersonBeneficiaryModel;
import com.fabrick.bussolino.model.bonifico.TaxReliefModel;
import com.fabrick.bussolino.request.bonifico.external.ExternalBonificoRequest;
import com.fabrick.bussolino.request.bonifico.internal.InternalBonificoRequest;
import com.fabrick.bussolino.response.external.ExternalBonificoResponse;
import com.fabrick.bussolino.utility.LoggerUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import static com.fabrick.bussolino.utility.LoggerUtility.logResponseCode;
import static com.fabrick.bussolino.utility.Utility.preCheckField;
import static com.fabrick.bussolino.utility.Utility.prepareHttpHeader;

@Service
public class BonificoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BonificoService.class);
    private final String API_BONIFICO_SERVICE = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers";
    private final RestTemplate restTemplate;

    public BonificoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ExternalBonificoResponse moneyTransfer(InternalBonificoRequest internalBonificoRequest) throws RestClientException, IllegalArgumentException, JsonProcessingException {
        LOGGER.info("Ricevuta richiesta di recupero saldo per l'account {}", internalBonificoRequest.getAccountId());
        preCheckBonifico(internalBonificoRequest);

        HttpEntity<ExternalBonificoRequest> entity = new HttpEntity<>(mockingTryCall(internalBonificoRequest), prepareHttpHeader());
        LoggerUtility.logChiamataServizioEsterno(API_BONIFICO_SERVICE, internalBonificoRequest.getAccountId().toString());
        ResponseEntity<ExternalBonificoResponse> response = restTemplate.exchange(API_BONIFICO_SERVICE.replace("{accountId}", internalBonificoRequest.getAccountId().toString()), HttpMethod.POST, entity, new ParameterizedTypeReference<ExternalBonificoResponse>() {
        });
        logResponseCode(response.getStatusCode().toString(), Objects.requireNonNull(response.getBody()).getPayload().toString());
        return response.getBody();
    }

    private String prepareBodyBonifico(InternalBonificoRequest internalBonificoRequest) throws JsonProcessingException {
        ExternalBonificoRequest externalBonificoRequest = mockingTryCall(internalBonificoRequest);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(externalBonificoRequest);
    }

    private ExternalBonificoRequest mockingTryCall(InternalBonificoRequest internalBonificoRequest) {
        ExternalBonificoRequest request = new ExternalBonificoRequest(CreditorModel.builder().build(), LocalDate.now(), "", "", 100L, "EUR", false, false, "SHA", "14537780", TaxReliefModel.builder().build(), LegalPersonBeneficiaryModel.builder().build());
        request.setAmount(Long.valueOf(internalBonificoRequest.getAmount()));
        request.getCreditor().setName(internalBonificoRequest.getReceiverName());
        request.setDescription(internalBonificoRequest.getDescription());
        request.setCurrency(internalBonificoRequest.getCurrency());
        request.setExecutionDate(LocalDate.parse(internalBonificoRequest.getExecutionDate().replace("-",""), DateTimeFormatter.BASIC_ISO_DATE));
        return request;
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
