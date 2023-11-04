package com.fabrick.bussolino.repository;

import com.fabrick.bussolino.dto.TransazioneDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransazioneRepository extends JpaRepository<TransazioneDTO, Long> {
}
