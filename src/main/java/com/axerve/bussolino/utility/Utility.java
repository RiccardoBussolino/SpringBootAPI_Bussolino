package com.axerve.bussolino.utility;

import com.axerve.bussolino.request.ExternalBonificoRequest;
import com.axerve.bussolino.response.internal.InternalBonificoResponse;
import com.axerve.bussolino.response.internal.InternalSaldoResponse;
import com.axerve.bussolino.response.external.ExternalSaldoResponse;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang3.StringUtils.isNumeric;


public class Utility {
    private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);
    public static InternalSaldoResponse prepareResponse(ExternalSaldoResponse resp) {
        return new InternalSaldoResponse(resp);
    }

    public static InternalBonificoResponse prepareResponse(ExternalBonificoRequest resp) {
        return new InternalBonificoResponse(resp);
    }
    public static void preCheckField(String fieldName, String fieldValue, int size) {
        if(StringUtils.isBlank(fieldValue) || (StringUtils.isNotBlank(fieldValue) && (fieldValue.length()!=size || !isNumeric(fieldValue)))) {
            LOGGER.error("Il cammpo {} non risulta formattato nella maniera corretta.",fieldName);
            throw new IllegalArgumentException();
        }
    }
}