package com.nttdata.btc.operation.app.repository;

import com.nttdata.btc.operation.app.model.entity.Operation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Class repository OperationRepository.
 *
 * @author lrs
 */
@Repository
public interface OperationRepository extends ReactiveMongoRepository<Operation, String> {
    /**
     * Method find by source account.
     *
     * @param parameter1 {@link String}
     * @return {@link Operation}
     */
    @Query("{'source_account' : ?0")
    Flux<Operation> findPositionalParameters(String parameter1);
}