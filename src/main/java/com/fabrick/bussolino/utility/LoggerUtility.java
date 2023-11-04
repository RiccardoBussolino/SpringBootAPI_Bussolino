package com.fabrick.bussolino.utility;

import com.fabrick.bussolino.response.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;

import java.util.Objects;

public class LoggerUtility {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtility.class);

    public static void logChiamataServizioEsterno(String url, String accountId, HttpEntity entity) {
        LOGGER.info("Avvio chiamata per recupero saldo, al servizio esterno {} per l'account {}", url, accountId);
        LOGGER.info ("HEADER:{}", entity.getHeaders());
        LOGGER.info("BODY: {}", Objects.requireNonNull(entity.getBody()));

    }
    public static void logJsonResponse(JsonResponse jsonResponse){
        if(jsonResponse.getStatus().is2xxSuccessful()){
            LOGGER.info("Il servizio esterno ha risposto correttamente - STATUS: {}", jsonResponse.getStatus().value());
            LOGGER.info("Response payloads: {}",jsonResponse.getPayload().toString());
        }
        else  {
            LOGGER.error("Il servizio esterno ha segnalto un errore - STATUS: {}", jsonResponse.getStatus().value());
            LOGGER.error("Response error: {}",jsonResponse.getError().toString());
            LOGGER.error("Response payload: {}", jsonResponse.getPayload()!=null?jsonResponse.getPayload().toString():"{}");
        }
    }

}
