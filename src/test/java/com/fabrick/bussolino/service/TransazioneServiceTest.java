package com.fabrick.bussolino.service;

import com.fabrick.bussolino.request.transazione.internal.InternalTransazioneRequest;
import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.response.transazione.external.ExternalTransazioneResponse;
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

import static com.fabrick.bussolino.utility.MockedObject.generateJsonResponseKOTransazione;
import static com.fabrick.bussolino.utility.MockedObject.generateJsonResponseTransazione;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransazioneServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private TransazioneService transazioneService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        transazioneService = new TransazioneService(restTemplate);
    }

    @Test
    public void testTransazione() {
        Long accountId = 12345678L;
        InternalTransazioneRequest internalTransazioneRequest = new InternalTransazioneRequest(accountId, "2022-12-18","2022-01-13");
        JsonResponse<ExternalTransazioneResponse> jsonResponse = generateJsonResponseTransazione();
        ResponseEntity<JsonResponse<ExternalTransazioneResponse>> responseEntity = ResponseEntity.ok(jsonResponse);
        when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<ParameterizedTypeReference<JsonResponse<ExternalTransazioneResponse>>>any())).thenReturn(responseEntity);
        JsonResponse<ExternalTransazioneResponse> result = transazioneService.transactionList(internalTransazioneRequest);
        assertEquals(jsonResponse, result);
    }

    @Test
    public void testTransazioneKO() {
        Long accountId = 12345678L;
        InternalTransazioneRequest internalTransazioneRequest = new InternalTransazioneRequest(accountId, "2022-12-18","2022-01-13");
        JsonResponse<ExternalTransazioneResponse> jsonResponse = generateJsonResponseKOTransazione();
        ResponseEntity<JsonResponse<ExternalTransazioneResponse>> responseEntity = ResponseEntity.ok(jsonResponse);
        when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<ParameterizedTypeReference<JsonResponse<ExternalTransazioneResponse>>>any())).thenReturn(responseEntity);
        JsonResponse<ExternalTransazioneResponse> result = transazioneService.transactionList(internalTransazioneRequest);
        assertEquals(jsonResponse, result);
    }

}
