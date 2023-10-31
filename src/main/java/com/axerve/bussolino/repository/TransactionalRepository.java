package com.axerve.bussolino.repository;

import com.axerve.bussolino.model.TransactionModel;
import org.springframework.data.repository.CrudRepository;

public interface TransactionalRepository extends CrudRepository<TransactionModel, Integer> {
}
