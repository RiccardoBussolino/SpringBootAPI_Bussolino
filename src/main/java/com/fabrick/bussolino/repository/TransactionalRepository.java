package com.fabrick.bussolino.repository;

import com.fabrick.bussolino.model.TransactionModel;
import org.springframework.data.repository.CrudRepository;

public interface TransactionalRepository extends CrudRepository<TransactionModel, Integer> {
}
