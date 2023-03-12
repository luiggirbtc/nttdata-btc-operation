package com.nttdata.btc.operation.app.controller;

import com.nttdata.btc.operation.app.model.request.OperationRequest;
import com.nttdata.btc.operation.app.model.request.UpdateOperationRequest;
import com.nttdata.btc.operation.app.model.response.OperationResponse;
import com.nttdata.btc.operation.app.service.OperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class OperationController.
 *
 * @author lrs
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/operation")
public class OperationController {
    /**
     * Inject dependency OperationService.
     */
    @Autowired
    private OperationService service;

    /**
     * Service find by id.
     *
     * @param id {@link String}
     * @return {@link OperationResponse}
     */
    @GetMapping("id/{id}")
    public Mono<ResponseEntity<OperationResponse>> findProductById(@PathVariable final String id) {
        return service.findById(id)
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Service create operation.
     *
     * @param request {@link OperationRequest}
     * @return {@link OperationResponse}
     */
    @PostMapping("/")
    public Mono<ResponseEntity<OperationResponse>> createProduct(@RequestBody final OperationRequest request) {
        log.info("Start CreateOperation.");
        return service.save(request)
                .map(p -> new ResponseEntity<>(p, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    /**
     * Service update a operation.
     *
     * @param request {@link UpdateOperationRequest}
     * @return {@link OperationResponse}
     */
    @PutMapping("/")
    public Mono<ResponseEntity<OperationResponse>> updateProduct(@RequestBody final UpdateOperationRequest request) {
        log.info("Start UpdateOperation.");
        return service.update(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    /**
     * Service return all operations.
     *
     * @return {@link OperationResponse}
     */
    @GetMapping("/")
    public Flux<OperationResponse> findAllProducts() {
        log.info("Start findAll Operations.");
        return service.findAll()
                .doOnNext(product -> log.info(product.toString()));
    }

    /**
     * Service delete operation.
     *
     * @param id {@link String}
     * @return {@link Void}
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable(value = "id") final String id) {
        return service.delete(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}