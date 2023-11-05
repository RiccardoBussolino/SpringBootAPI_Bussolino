package com.fabrick.bussolino.controller;


import com.fabrick.bussolino.dto.TransazioneDTO;
import com.fabrick.bussolino.request.bonifico.internal.InternalBonificoRequest;
import com.fabrick.bussolino.request.transazione.internal.InternalTransazioneRequest;
import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.response.bonifico.BonificoResponseConverter;
import com.fabrick.bussolino.response.bonifico.external.ExternalBonificoResponse;
import com.fabrick.bussolino.response.bonifico.internal.InternalBonificoResponse;
import com.fabrick.bussolino.response.saldo.SaldoResponseConverter;
import com.fabrick.bussolino.response.saldo.external.ExternalSaldoResponse;
import com.fabrick.bussolino.response.saldo.internal.InternalSaldoResponse;
import com.fabrick.bussolino.response.transazione.TransazioneResponseConverter;
import com.fabrick.bussolino.response.transazione.external.ExternalTransazioneResponse;
import com.fabrick.bussolino.response.transazione.internal.InternalTransazioneResponse;
import com.fabrick.bussolino.service.db.TransazionePersistenceService;
import com.fabrick.bussolino.service.ws.BonificoService;
import com.fabrick.bussolino.service.ws.SaldoService;
import com.fabrick.bussolino.service.ws.TransazioneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fabrick.bussolino.service.db.TransazionePersistenceService.convertTransazioneToDTO;
import static com.fabrick.bussolino.utility.Constant.*;

@RestController
@RequestMapping(BASE_URL_REQUEST_MAPPING)
@AllArgsConstructor
public class ApiController {
    private final SaldoService saldoService;
    private final BonificoService bonificoService;
    private final TransazioneService transazioneService;
    private final TransazionePersistenceService transazionePersistenceService;


    /*
     Descrizione: Endpoint per la lettura del saldo relativo ad un accountId fornito in input.
     * input:
         AccountId - identificativo account del quale si vuole conoscere il saldo
     * output:
        Saldo - Importo del saldo presente sull'account.
     * */
    @GetMapping(value = API_LETTURA_SALDO)
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse<InternalSaldoResponse> getSaldo(@RequestParam Long accountId) {
        JsonResponse<ExternalSaldoResponse> response = saldoService.getSaldo(accountId);
        SaldoResponseConverter saldoResponseConverter = new SaldoResponseConverter(response);
        return saldoResponseConverter.convertResponse();
    }

    /*
     Descrizione: Endpoint per pianificare un bonifico in uscita indicando destinatario ed importo
     * input:
         accountId -accountId destinatario
         receiverName - Nome destinatario
         description - Descrizione del pagamento
         currency - Valuta
         amount - Importo
         executionDate - Data di esecuzione
     * output:
         Esito del bonifico + informazioni
     */
    @GetMapping(value = API_BONIFICO)
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse<InternalBonificoResponse> moneyTransfer(InternalBonificoRequest internalBonificoRequest) {
        JsonResponse<ExternalBonificoResponse> response = bonificoService.moneyTransfer(internalBonificoRequest);
        BonificoResponseConverter bonificoResponseConverter = new BonificoResponseConverter(response, internalBonificoRequest.getAccountId());
        return bonificoResponseConverter.convertResponse();
    }

    /*
 Descrizion: Endpoint per l'estrazione dei movimenti effettuati da un determinato utente durante un periodo
 * input:
       accountId -accountId destinatario
     fromAccountingDate - data inizio periodo di ricerca
     toAccountingDate - data fine periodo di ricerca
 * output:
 *  Lista di movimenti effettuate dall'accountId durante per il periodo delimitato dalle date ricevute in input.
 * */
    @GetMapping(value = API_LISTA_TRANSAZIONI)
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse<InternalTransazioneResponse> transactionList(InternalTransazioneRequest internalTransazioneRequest) {
        JsonResponse<ExternalTransazioneResponse> response = transazioneService.transactionList(internalTransazioneRequest);
        TransazioneResponseConverter transazioneResponseConverter = new TransazioneResponseConverter(response);
       JsonResponse<InternalTransazioneResponse> internalTransazioneResponseJsonResponse= transazioneResponseConverter.convertResponse();
       if(!internalTransazioneResponseJsonResponse.getStatus().is2xxSuccessful()) {
           transazionePersistenceService.saveAllTransaction(convertTransazioneToDTO(internalTransazioneResponseJsonResponse));
       }
   return internalTransazioneResponseJsonResponse;
    }
}
