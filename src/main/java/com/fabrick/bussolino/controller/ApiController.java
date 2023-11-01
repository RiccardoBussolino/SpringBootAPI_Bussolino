package com.fabrick.bussolino.controller;


import com.fabrick.bussolino.model.bonifico.AcccountModel;
import com.fabrick.bussolino.request.bonifico.internal.InternalBonificoRequest;
import com.fabrick.bussolino.response.external.ExternalBonificoResponse;
import com.fabrick.bussolino.response.external.ExternalSaldoResponse;
import com.fabrick.bussolino.response.internal.InternalBonificoResponse;
import com.fabrick.bussolino.response.internal.InternalSaldoResponse;
import com.fabrick.bussolino.service.BonificoService;
import com.fabrick.bussolino.service.SaldoService;
import com.fabrick.bussolino.utility.Utility;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ApiController {
/*    private final TransactionalRepository repository;

    public AccountController(TransactionalRepository repository) {
        this.repository = repository;
    }
*/
    @GetMapping(value = "/letturaSaldo")
    @ResponseStatus(HttpStatus.OK)
    public InternalSaldoResponse getSaldo(@RequestParam Long accountId) {
        SaldoService saldoService = new SaldoService(new RestTemplate());
        ExternalSaldoResponse response = saldoService.getSaldo(accountId);
        return Utility.prepareResponse(response);
    }

    @PostMapping(value = "/bonifico")
    @ResponseStatus(HttpStatus.OK)
    public InternalBonificoResponse Bonifico(@RequestParam Long accountId, @RequestBody InternalBonificoRequest bonificoRequest) {
        BonificoService bonificoService = new BonificoService(new RestTemplate());
        ExternalBonificoResponse response = bonificoService.moneyTransfer(accountId);
        return Utility.prepareResponse(response);
    }
}
