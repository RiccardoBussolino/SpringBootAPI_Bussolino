package com.fabrick.bussolino.utility;


import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.parseMediaTypes;


public class Utility {

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


}