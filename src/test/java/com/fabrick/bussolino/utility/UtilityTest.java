package com.fabrick.bussolino.utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import java.util.Objects;


public class UtilityTest {
    @Test
    public void testPrepareHttpHeaders(){
        HttpHeaders httpHeaders= Utility.prepareHttpHeader();
        Assertions.assertNotNull(httpHeaders);
        Assertions.assertEquals(Objects.requireNonNull(httpHeaders.get("Auth-Schema")).toString(), "[S2S]");
        Assertions.assertEquals(Objects.requireNonNull(httpHeaders.get("Content-Type")).toString(), "[application/json]");
        Assertions.assertEquals(Objects.requireNonNull(httpHeaders.get("Transfer-Encoding")).toString(), "[chunked]");
        Assertions.assertEquals(Objects.requireNonNull(httpHeaders.get("Api-Key")).toString(), "[FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP]");
        Assertions.assertEquals(Objects.requireNonNull(httpHeaders.get("idChiave")).toString(), "[3202]");
        Assertions.assertEquals(Objects.requireNonNull(httpHeaders.get("X-Time-Zone")).toString(), "[]");
    }
}
