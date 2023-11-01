package com.fabrick.bussolino.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtility {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtility.class);

    public static void logResponseCode(String statusCode, String payloads) {

        if (("200").equalsIgnoreCase(statusCode)) {
            LOGGER.info("Il servizio esterno ha risposto correttamente - STATUS: {}", statusCode);
        } else {
            LOGGER.error("Il servizio esterno ha segnalto un errore - STATUS: {}", statusCode);
        }
        LOGGER.info("Response payloads: {}",payloads);
    }
    public static void logChiamataServizioEsterno(String service,String accountId) {
        LOGGER.info("Avvio chiamata per recupero saldo, al servizio esterno {} per l'account {}", service, accountId);
    }
}
