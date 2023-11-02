package com.fabrick.bussolino.service;

import com.fabrick.bussolino.model.bonifico.*;
import com.fabrick.bussolino.request.bonifico.external.ExternalBonificoRequest;
import com.fabrick.bussolino.request.bonifico.internal.InternalBonificoRequest;
import com.fabrick.bussolino.response.external.ExternalBonificoResponse;
import com.fabrick.bussolino.utility.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.util.Objects;

import static com.fabrick.bussolino.utility.LoggerUtility.logChiamataServizioEsterno;
import static com.fabrick.bussolino.utility.LoggerUtility.logResponseCode;
import static com.fabrick.bussolino.utility.Utility.preCheckField;
import static com.fabrick.bussolino.utility.Utility.prepareHttpHeader;

@Service
public class BonificoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BonificoService.class);
    private final String API_BONIFICO_SERVICE = "https://sandbox.platfr.io//api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers";
    private final RestTemplate restTemplate;

    public BonificoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ExternalBonificoResponse moneyTransfer(InternalBonificoRequest internalBonificoRequest) throws RestClientException, IllegalArgumentException {
        LOGGER.info("Ricevuta richiesta di recupero saldo per l'account {}", internalBonificoRequest.getAccountId());
        preCheckBonifico(internalBonificoRequest);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).serializeNulls()
                .create();
        ExternalBonificoRequest externalBonificoRequest= mockingTryCallExtended(internalBonificoRequest);
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(externalBonificoRequest), prepareHttpHeader());

        String url = API_BONIFICO_SERVICE.replace("{accountId}", internalBonificoRequest.getAccountId().toString());
        logChiamataServizioEsterno(url, internalBonificoRequest.getAccountId().toString(),entity.getHeaders().toString(), entity.getBody());
        ResponseEntity<ExternalBonificoResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<ExternalBonificoResponse>() {
        });
        logResponseCode(response.getStatusCode().toString(), Objects.requireNonNull(response.getBody()).getPayload().toString());
        return response.getBody();
    }

    private ExternalBonificoRequest mockingTryCallExtended(InternalBonificoRequest internalBonificoRequest) {
        ExternalBonificoRequest request = new ExternalBonificoRequest();
        CreditorModel creditorModel = new CreditorModel();
        creditorModel.setName(internalBonificoRequest.getReceiverName());
        creditorModel.setAccount(new AcccountModel("IT23A0336844430152923804660", "SELBIT2BXXX"));
        creditorModel.setAddress(new AddressModel(null, null, null));
        request.setCreditor(creditorModel);
        request.setExecutionDate(LocalDate.parse(internalBonificoRequest.getExecutionDate().replace("-", ""), DateTimeFormatter.BASIC_ISO_DATE));
        request.setUri("REMITTANCE_INFORMATION");
        request.setDescription(internalBonificoRequest.getDescription());
        request.setAmount(Long.valueOf(internalBonificoRequest.getAmount()));
        request.setCurrency(internalBonificoRequest.getCurrency());
        request.setIsUrgent(false);
        request.setIsInstant(false);
        request.setFeeType("SHA");
        request.setFeeAccountId("45685475");
        request.setTaxRelief(new TaxReliefId("L449", false, "56258745832", "NATURAL_PERSON", new NaturalPersonBeneficiaryModel("MRLFNC81L04A859L", null, null, null, null), new LegalPersonBeneficiaryModel(null, null)));
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
