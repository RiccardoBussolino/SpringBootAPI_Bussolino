package com.fabrick.bussolino.service;

import com.fabrick.bussolino.request.bonifico.internal.InternalBonificoRequest;
import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.response.bonifico.external.ExternalBonificoResponse;
import com.fabrick.bussolino.service.ws.BonificoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.fabrick.bussolino.utility.MockedObject.generateJsonResponseBonifico;
import static com.fabrick.bussolino.utility.MockedObject.generateJsonResponseKOBonifico;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BonificoServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private BonificoService bonificoService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        bonificoService = new BonificoService(restTemplate);
    }

    @Test
    public void testBonifico() {
        Long accountId = 12345678L;
        InternalBonificoRequest internalBonificoRequest = new InternalBonificoRequest(accountId, "Test", "DescrizioneTest", "TST", "0", "9999-12-31");
        JsonResponse<ExternalBonificoResponse> jsonResponse = generateJsonResponseBonifico();
        ResponseEntity<JsonResponse<ExternalBonificoResponse>> responseEntity = ResponseEntity.ok(jsonResponse);
        when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<ParameterizedTypeReference<JsonResponse<ExternalBonificoResponse>>>any())).thenReturn(responseEntity);
        JsonResponse<ExternalBonificoResponse> result = bonificoService.moneyTransfer(internalBonificoRequest);
        assertEquals(jsonResponse, result);
    }

    @Test
    public void testBonificoKO() {
        Long accountId = 12345678L;
        InternalBonificoRequest internalBonificoRequest = new InternalBonificoRequest(accountId, "Test", "DescrizioneTest", "TST", "0", "9999-12-31");
        JsonResponse<ExternalBonificoResponse> jsonResponse = generateJsonResponseKOBonifico();
        ResponseEntity<JsonResponse<ExternalBonificoResponse>> responseEntity = ResponseEntity.ok(jsonResponse);
        when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<ParameterizedTypeReference<JsonResponse<ExternalBonificoResponse>>>any())).thenReturn(responseEntity);
        JsonResponse<ExternalBonificoResponse> result = bonificoService.moneyTransfer(internalBonificoRequest);
        assertEquals(jsonResponse, result);
    }

}
