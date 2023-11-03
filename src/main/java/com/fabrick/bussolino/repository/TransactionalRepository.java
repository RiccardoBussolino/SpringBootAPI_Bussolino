package com.fabrick.bussolino.repository;

import com.fabrick.bussolino.dto.TransactionDTO;
import org.springframework.data.repository.CrudRepository;

public interface TransactionalRepository extends CrudRepository<TransactionDTO, Integer> {
}
