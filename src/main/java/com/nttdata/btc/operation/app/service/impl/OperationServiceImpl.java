package com.nttdata.btc.operation.app.service.impl;

import com.nttdata.btc.operation.app.model.entity.Operation;
import com.nttdata.btc.operation.app.model.request.OperationRequest;
import com.nttdata.btc.operation.app.model.request.UpdateOperationRequest;
import com.nttdata.btc.operation.app.model.response.OperationResponse;
import com.nttdata.btc.operation.app.repository.OperationRepository;
import com.nttdata.btc.operation.app.service.OperationService;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.nttdata.btc.operation.app.util.constant.Constants.DEFAULT_FALSE;
import static com.nttdata.btc.operation.app.util.enums.CategoryOperationEnum.findOperationCategory;
import static com.nttdata.btc.operation.app.util.enums.TypeOperationEnum.findOperationType;

/**
 * Class implement methods from OperationService.
 *
 * @author lrs
 */
@Service
public class OperationServiceImpl implements OperationService {
    /**
     * Inject dependency {@link OperationRepository}
     */
    @Autowired
    private OperationRepository repository;

    /**
     * This method return all operations.
     *
     * @return {@link List<OperationResponse>}
     */
    @Override
    public Flux<OperationResponse> findAll() {
        return repository.findAll().filter(Operation::isStatus)
                .map(c -> buildOperationR.apply(c))
                .onErrorReturn(new OperationResponse());
    }

    /**
     * This method find a operation by id.
     *
     * @param id {@link String}
     * @return {@link OperationResponse}
     */
    @Override
    public Mono<OperationResponse> findById(String id) {
        return repository.findById(id)
                .filter(Operation::isStatus)
                .map(e -> buildOperationR.apply(e))
                .onErrorReturn(new OperationResponse());
    }

    /**
     * This method save a operation.
     *
     * @param request {@link OperationRequest}
     * @return {@link OperationResponse}
     */
    @Override
    public Mono<OperationResponse> save(OperationRequest request) {
        return repository.save(buildOperation.apply(request))
                .flatMap(entity -> Mono.just(buildOperationR.apply(entity)))
                .onErrorReturn(new OperationResponse());
    }

    /**
     * This method update status from operation.
     *
     * @param id {@link String}
     * @return {@link Void}
     */
    @Override
    public Mono<Void> delete(String id) {
        return repository.findById(id).filter(Operation::isStatus)
                .map(e -> updateStatus.apply(e, DEFAULT_FALSE))
                .flatMap(e -> repository.delete(e));
    }

    /**
     * This method update a operation.
     *
     * @param request {@link UpdateOperationRequest}
     * @return {@link OperationResponse}
     */
    @Override
    public Mono<OperationResponse> update(UpdateOperationRequest request) {
        return repository.findById(request.getId_operation())
                .map(entity -> updateOperation.apply(request, entity))
                .flatMap(operation -> repository.save(operation))
                .flatMap(aupdated -> Mono.just(buildOperationR.apply(aupdated)))
                .onErrorReturn(new OperationResponse());
    }

    /**
     * BiFunction update Operation.
     */
    BiFunction<UpdateOperationRequest, Operation, Operation> updateOperation = (request, bean) -> {
        bean.setCategory(request.getCategory());
        bean.setType(request.getType());
        bean.setSource_account(request.getSource_account());
        bean.setTarget_account(request.getTarget_account());
        bean.setCurrency(request.getCurrency());
        bean.setAmount(request.getAmount());
        bean.setDescription(request.getDescription());
        return bean;
    };

    /**
     * BiFunction updateStatus from Operation.
     */
    BiFunction<Operation, Boolean, Operation> updateStatus = (product, status) -> {
        product.setStatus(status);
        return product;
    };

    /**
     * Function build new Operation.
     */
    Function<OperationRequest, Operation> buildOperation = request -> new Operation(request.getCategory(), request.getType(),
            request.getSource_account(), request.getTarget_account(), request.getCurrency(), request.getAmount(), request.getDescription());

    /**
     * Function build new OperationResponse.
     */
    Function<Operation, OperationResponse> buildOperationR = entity -> {
        OperationResponse response = new OperationResponse();
        response.setId_operation(entity.getId_operation());
        response.setDescription(entity.getDescription());
        response.setCategory(entity.getCategory());
        response.setCategoryDescription(
                findOperationCategory(entity.getCategory().intValue()).getDescription());
        response.setType(entity.getType());
        response.setTypeDescription(
                findOperationType(entity.getType().intValue()).getDescription());
        response.setSource_account(entity.getSource_account());
        response.setTarget_account(entity.getTarget_account());
        response.setCurrency(entity.getCurrency());
        response.setAmount(entity.getAmount());
        response.setRegister_date(entity.getRegister_date());
        response.setStatus(entity.isStatus());
        return response;
    };
}