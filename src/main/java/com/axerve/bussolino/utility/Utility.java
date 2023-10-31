package com.axerve.bussolino.utility;

import com.axerve.bussolino.response.internal.InternalSaldoResponse;
import com.axerve.bussolino.response.external.ExternalSaldoResponse;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {
    private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);
    public static InternalSaldoResponse prepareResponse(ExternalSaldoResponse resp) {
        return new InternalSaldoResponse(resp);
    }
    public static void preCheckField(String fieldName, String fieldValue, int size) {
        if(StringUtils.isBlank(fieldValue) || (StringUtils.isNotBlank(fieldValue) && fieldValue.length()!=size)) {
            LOGGER.error("Il cammpo {} non risulta formattata nella maniera corretta.",fieldName);
            throw new IllegalArgumentException();
        }
    }
}