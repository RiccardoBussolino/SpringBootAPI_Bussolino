package com.fabrick.bussolino.utility;


import com.fabrick.bussolino.model.bonifico.*;
import com.fabrick.bussolino.request.bonifico.external.ExternalBonificoRequest;
import com.fabrick.bussolino.request.bonifico.internal.InternalBonificoRequest;
import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.response.saldo.external.ExternalSaldoResponse;
import com.fabrick.bussolino.response.bonifico.external.ExternalBonificoResponse;
import com.fabrick.bussolino.response.transazione.external.ExternalTransazioneResponse;
import com.fabrick.bussolino.response.bonifico.internal.InternalBonificoResponse;
import com.fabrick.bussolino.response.saldo.internal.InternalSaldoResponse;
import com.fabrick.bussolino.response.transazione.internal.InternalTransazioneResponse;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.springframework.http.MediaType.*;


public class Utility {
    private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);

    public static void preCheckField(String fieldName, String fieldValue, int size, boolean isNumeric) {
        if (StringUtils.isBlank(fieldValue) || (StringUtils.isNotBlank(fieldValue) && (size != -1 && fieldValue.length() != size || (isNumeric && !isNumeric(fieldValue))))) {
            LOGGER.error("Il cammpo {} non risulta formattato nella maniera corretta.", fieldName);
            throw new IllegalArgumentException();
        }
    }

    public static HttpHeaders prepareHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(parseMediaTypes(APPLICATION_JSON_VALUE));
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Transfer-Encoding", "chunked");
        httpHeaders.add("Auth-Schema", "S2S");
        httpHeaders.add("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        httpHeaders.add("idChiave", "3202");
        httpHeaders.add("X-Time-Zone", "");

        return httpHeaders;
    }


    public static ExternalBonificoRequest mockingTryCallExtended(InternalBonificoRequest internalBonificoRequest) {
        /*
        * Sarebbe possibile richiamare l'API relativa al recupero di tutti gli account e quindi le informazioni, per adesso si procede con un mocking.
        *API di riferimento "GET CUSTOMER CASH ACCOUNTS": https://sandbox.platfr.ioT/api/gbs/customer/v4.0/myself/accounts
        * response: {
    "status": {
        "code": "OK",
        "description": "Accounts list retrieved successfully"
    },
    "error": {
        "description": ""
    },
    "payload": [
        {
            "accountId": "14537780",
            "iban": "IT40L0326822311052923800661",
            "account": "1152923800661",
            "accountMasked": "Not Available",
            "accountAlias": "Test api",
            "productName": "Conto Websella",
            "accountHolderName": "LUCA TERRIBILE",
            "currency": "Euro"
        }
    ]
}
        * */


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
}