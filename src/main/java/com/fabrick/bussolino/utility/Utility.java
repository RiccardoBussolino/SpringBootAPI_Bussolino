package com.fabrick.bussolino.utility;


import com.fabrick.bussolino.model.bonifico.AcccountModel;
import com.fabrick.bussolino.response.external.ExternalBonificoResponse;
import com.fabrick.bussolino.response.external.ExternalSaldoResponse;
import com.fabrick.bussolino.response.internal.InternalBonificoResponse;
import com.fabrick.bussolino.response.internal.InternalSaldoResponse;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

import static org.apache.commons.lang3.StringUtils.isNumeric;


public class Utility {
    private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);
    public static InternalSaldoResponse prepareResponse(ExternalSaldoResponse resp) {
        return new InternalSaldoResponse(resp);
    }

    public static InternalBonificoResponse prepareResponse(ExternalBonificoResponse resp,Long accountId) {
        return new InternalBonificoResponse(resp, accountId);
    }
    public static void preCheckField(String fieldName, String fieldValue, int size,boolean isNumeric) {
        if(StringUtils.isBlank(fieldValue) || (StringUtils.isNotBlank(fieldValue) && (size!=-1 && fieldValue.length()!=size || (isNumeric && !isNumeric(fieldValue))))) {
            LOGGER.error("Il cammpo {} non risulta formattato nella maniera corretta.",fieldName);
            throw new IllegalArgumentException();
        }
    }
    public static HttpHeaders prepareHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("Auth-Schema", "S2S");
        httpHeaders.add("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        httpHeaders.add("idChiave", "3202");
        httpHeaders.add("X-Time-Zone","");

        return httpHeaders;
    }

}