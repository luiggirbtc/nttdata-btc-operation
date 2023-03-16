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
     * @param source_account {@link String}
     * @return {@link Operation}
     */
    @Query("mongo query")
    Flux<Operation> findBy(String source_account);
}