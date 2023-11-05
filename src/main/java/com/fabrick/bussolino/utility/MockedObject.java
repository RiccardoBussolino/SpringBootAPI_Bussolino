package com.fabrick.bussolino.utility;

import com.fabrick.bussolino.model.bonifico.*;
import com.fabrick.bussolino.model.transazione.TransactionModel;
import com.fabrick.bussolino.model.transazione.TransactionType;
import com.fabrick.bussolino.request.bonifico.external.ExternalBonificoRequest;
import com.fabrick.bussolino.request.bonifico.internal.InternalBonificoRequest;
import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.response.bonifico.external.ExternalBonificoResponse;
import com.fabrick.bussolino.response.saldo.external.ExternalSaldoResponse;
import com.fabrick.bussolino.response.transazione.external.ExternalTransazioneResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MockedObject {
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

    public static JsonResponse<ExternalSaldoResponse> generateJsonResponseSaldo() {
        return new JsonResponse<>(HttpStatus.OK, new ArrayList<>(), new ExternalSaldoResponse(new Date(), "1", "1", "EUR"));
    }

    public static JsonResponse<ExternalSaldoResponse> generateJsonResponseKOSaldo() {
        return new JsonResponse<>(HttpStatus.BAD_REQUEST, List.of("Errore accountId mal formattato"), new ExternalSaldoResponse());
    }

    public static JsonResponse<ExternalBonificoResponse> generateJsonResponseBonifico() {
        return new JsonResponse<>(HttpStatus.OK, new ArrayList<>(), new ExternalBonificoResponse());
    }

    public static JsonResponse<ExternalBonificoResponse> generateJsonResponseKOBonifico() {
        return new JsonResponse<>(HttpStatus.BAD_REQUEST, List.of("Errore accountId mal formattato"), new ExternalBonificoResponse());
    }

    public static JsonResponse<ExternalTransazioneResponse> generateJsonResponseTransazione() {
        TransactionType type = new TransactionType();
        type.setEnumeration(TransactionType.Enumeration.GBS_TRANSACTION_TYPE);
        type.setValue(TransactionType.Value.GBS_ACCOUNT_TRANSACTION_TYPE_0009);
        return new JsonResponse<>(HttpStatus.OK, new ArrayList<>(), new ExternalTransazioneResponse(Arrays.asList(new TransactionModel("12345",
                "OPE123",
                new Date(),
                new Date(),
                type,
                100,
                "EUR",
                "Causale transazione"))));
    }

    public static JsonResponse<ExternalTransazioneResponse> generateJsonResponseKOTransazione() {
        return new JsonResponse<>(HttpStatus.BAD_REQUEST, List.of("Errore non è stato possibile recuperare le informazioni richieste"), new ExternalTransazioneResponse(new ArrayList<>()));
    }
}