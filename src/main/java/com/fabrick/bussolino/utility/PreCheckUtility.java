package com.fabrick.bussolino.utility;

import com.fabrick.bussolino.request.bonifico.internal.InternalBonificoRequest;
import com.fabrick.bussolino.request.transazione.internal.InternalTransazioneRequest;
import io.micrometer.common.util.StringUtils;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class PreCheckUtility {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Utility.class);
    public static void preCheckBonifico(InternalBonificoRequest internalBonificoRequest) throws IllegalArgumentException {
        preCheckField("accountId", internalBonificoRequest.getAccountId().toString(), 8, true);
        preCheckField("receiverName", internalBonificoRequest.getReceiverName(), -1, false);
        preCheckField("description", internalBonificoRequest.getDescription(), -1, false);
        preCheckField("currency", internalBonificoRequest.getCurrency(), -1, false);
        preCheckField("amount", internalBonificoRequest.getAmount(), -1, false);
        preCheckField("amount", internalBonificoRequest.getExecutionDate(), -1, false);
    }

    public static void preCheckTransazione(InternalTransazioneRequest internalTransazioneRequest) throws IllegalArgumentException {
        preCheckField("accountId", internalTransazioneRequest.getAccountId().toString(), 8, true);
        preCheckField("fromAccountingDate", internalTransazioneRequest.getFromAccountingDate(), -1, false);
        preCheckField("toAccountingDate", internalTransazioneRequest.getToAccountingDate(), -1, false);
    }

    public static void preCheckField(String fieldName, String fieldValue, int size, boolean isNumeric) {
        if (StringUtils.isBlank(fieldValue) || (StringUtils.isNotBlank(fieldValue) && (size != -1 && fieldValue.length() != size || (isNumeric && !isNumeric(fieldValue))))) {
            LOGGER.error("Il cammpo {} non risulta formattato nella maniera corretta.", fieldName);
            throw new IllegalArgumentException();
        }
    }
}
