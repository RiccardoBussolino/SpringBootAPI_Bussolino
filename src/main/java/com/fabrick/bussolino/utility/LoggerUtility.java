package com.fabrick.bussolino.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtility {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtility.class);

    public static void logResponseCode(String statusCode,String errors, String payloads) {

        if (("200").equalsIgnoreCase(statusCode)) {
            LOGGER.info("Il servizio esterno ha risposto correttamente - STATUS: {}", statusCode);
            LOGGER.info("Response payloads: {}",payloads);
        } else {
            LOGGER.error("Il servizio esterno ha segnalto un errore - STATUS: {}", statusCode);
            LOGGER.error("Response error: {}",errors);
        }

    }
    public static void logChiamataServizioEsterno(String service,String accountId, String header, String body) {
        LOGGER.info("Avvio chiamata per recupero saldo, al servizio esterno {} per l'account {}", service, accountId);
        LOGGER.info ("HEADER:{}",header);
        LOGGER.info("BODY: {}",body);

    }
}
