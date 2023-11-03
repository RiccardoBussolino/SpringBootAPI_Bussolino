package com.fabrick.bussolino.controller;


import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.response.bonifico.BonificoResponseConverter;
import com.fabrick.bussolino.response.saldo.SaldoResponseConverter;
import com.fabrick.bussolino.response.saldo.external.ExternalSaldoResponse;
import com.fabrick.bussolino.response.bonifico.external.ExternalBonificoResponse;
import com.fabrick.bussolino.response.transazione.TransazioneResponseConverter;
import com.fabrick.bussolino.response.transazione.external.ExternalTransazioneResponse;
import com.fabrick.bussolino.request.bonifico.internal.InternalBonificoRequest;
import com.fabrick.bussolino.request.transazione.internal.InternalTransazioneRequest;
import com.fabrick.bussolino.response.bonifico.internal.InternalBonificoResponse;
import com.fabrick.bussolino.response.saldo.internal.InternalSaldoResponse;
import com.fabrick.bussolino.response.transazione.internal.InternalTransazioneResponse;
import com.fabrick.bussolino.service.BonificoService;
import com.fabrick.bussolino.service.SaldoService;
import com.fabrick.bussolino.service.TransazioneService;
import com.fabrick.bussolino.utility.Utility;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping(value = "/letturaSaldo")
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse<InternalSaldoResponse> getSaldo(@RequestParam Long accountId) {
        SaldoService saldoService = new SaldoService(new RestTemplate());
       JsonResponse<ExternalSaldoResponse> response = saldoService.getSaldo(accountId);
       SaldoResponseConverter saldoResponseConverter= new SaldoResponseConverter(response);
        return saldoResponseConverter.convertResponse();
    }

    @GetMapping(value = "/bonifico")
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse<InternalBonificoResponse> moneyTransfer(InternalBonificoRequest internalBonificoRequest) {
        BonificoService bonificoService = new BonificoService(new RestTemplate());
       JsonResponse<ExternalBonificoResponse> response = bonificoService.moneyTransfer(internalBonificoRequest);
        BonificoResponseConverter bonificoResponseConverter= new BonificoResponseConverter(response,internalBonificoRequest.getAccountId());
        return bonificoResponseConverter.convertResponse();
    }
    @GetMapping(value = "/letturaTransazioni")
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse<InternalTransazioneResponse> transactionList(InternalTransazioneRequest internalTransazioneRequest) {
        TransazioneService transazioneService = new TransazioneService(new RestTemplate());
        JsonResponse<ExternalTransazioneResponse> response = transazioneService.transactionList(internalTransazioneRequest);
        TransazioneResponseConverter transazioneResponseConverter=new TransazioneResponseConverter(response);
        return transazioneResponseConverter.convertResponse();
    }
}
