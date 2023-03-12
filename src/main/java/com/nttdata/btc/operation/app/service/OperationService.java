package com.nttdata.btc.operation.app.service;

import com.nttdata.btc.operation.app.model.request.OperationRequest;
import com.nttdata.btc.operation.app.model.request.UpdateOperationRequest;
import com.nttdata.btc.operation.app.model.response.OperationResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class service OperationService.
 *
 * @author lrs.
 */
public interface OperationService {
    /**
     * Method findAll.
     */
    Flux<OperationResponse> findAll();

    /**
     * Method findById.
     */
    Mono<OperationResponse> findById(String id);

    /**
     * Method save.
     */
    Mono<OperationResponse> save(OperationRequest request);

    /**
     * Method Delete.
     */
    Mono<Void> delete(String id);

    /**
     * Method update operation.
     */
    Mono<OperationResponse> update(UpdateOperationRequest request);
}