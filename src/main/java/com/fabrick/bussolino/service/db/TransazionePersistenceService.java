package com.fabrick.bussolino.service.db;

import com.fabrick.bussolino.dto.TransazioneDTO;
import com.fabrick.bussolino.model.transazione.TransactionModel;
import com.fabrick.bussolino.repository.TransazioneRepository;
import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.response.transazione.internal.InternalTransazioneResponse;
import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransazionePersistenceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransazionePersistenceService.class);
    private final TransazioneRepository transazioneRepository;

    @Autowired
    public TransazionePersistenceService(TransazioneRepository transazioneRepository) {
        this.transazioneRepository = transazioneRepository;
    }

    public void saveAllTransaction(List<TransazioneDTO> listTransazione) {
        try {
            List<TransazioneDTO> response = transazioneRepository.saveAll(listTransazione);
            if (response.size() == listTransazione.size())
                LOGGER.info("SUCCESS: Tutte le transazioni sono state salvate. Transazioni salvate: {}", response.size());
            else
                LOGGER.warn("ATTENZIONE: Non tutte le transazioni sono state salvate su base dati. \n" +
                        " Transazioni input:{} \n" +
                        "Transazioni salvate: {} ", listTransazione.size(), response.size());
        } catch (JDBCException ex) {
            LOGGER.error("ATTENZIONE: errore durante il salvataggio dei dati relativo alle transazioni. \n SQL: {} \n Errore: {}", ex.getSQL(), ex.getErrorMessage());

        }
    }

    public static List<TransazioneDTO> convertTransazioneToDTO(JsonResponse<InternalTransazioneResponse> internalResponse) {
        List<TransazioneDTO> dto = new ArrayList<>();
        if (internalResponse != null && internalResponse.getPayload() != null) {
            for (TransactionModel obj : internalResponse.getPayload().getList()) {
                dto.add(new TransazioneDTO(obj.getTransactionId(), obj.getOperationId(), new Date(obj.getAccountingDate().getTime()), new Date(obj.getValueDate().getTime()), obj.getType().toString(), obj.getAmount(), obj.getCurrency(), obj.getDescription()));
            }
        }
        return dto;
    }

}
