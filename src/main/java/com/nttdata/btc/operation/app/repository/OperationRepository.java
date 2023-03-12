package com.nttdata.btc.operation.app.repository;

import com.nttdata.btc.operation.app.model.entity.Operation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Class repository OperationRepository.
 *
 * @author lrs
 */
@Repository
public interface OperationRepository extends ReactiveMongoRepository<Operation, String> {
}