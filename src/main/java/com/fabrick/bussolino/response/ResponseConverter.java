package com.fabrick.bussolino.response;

import com.fabrick.bussolino.response.bonifico.external.ExternalBonificoResponse;
import com.fabrick.bussolino.response.bonifico.internal.InternalBonificoResponse;
import com.fabrick.bussolino.response.saldo.external.ExternalSaldoResponse;
import com.fabrick.bussolino.response.saldo.internal.InternalSaldoResponse;
import com.fabrick.bussolino.response.transazione.external.ExternalTransazioneResponse;
import com.fabrick.bussolino.response.transazione.internal.InternalTransazioneResponse;

public interface ResponseConverter<T, U> {
    public JsonResponse<T> convertResponse();
}

