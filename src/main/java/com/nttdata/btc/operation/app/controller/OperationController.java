package com.nttdata.btc.operation.app.controller;

import com.nttdata.btc.operation.app.model.request.OperationRequest;
import com.nttdata.btc.operation.app.model.request.UpdateOperationRequest;
import com.nttdata.btc.operation.app.model.response.OperationResponse;
import com.nttdata.btc.operation.app.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class OperationController.
 *
 * @author lrs
 */
@Slf4j
@Tag(name = "Operation")
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
    @Operation(summary = "Get a operation by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found operation",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OperationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
    @GetMapping(value = "id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<OperationResponse> findOperationById(@PathVariable final String id) {
        log.info("Start service findOperationById.");
        return service.findById(id);
    }

    /**
     * Service find by source account.
     *
     * @param code {@link String}
     * @return {@link OperationResponse}
     */
    @Operation(summary = "Get a operations by source account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found operation",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OperationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
    @GetMapping(value = "code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<OperationResponse> findOperationBySourceAcc(@PathVariable final String code) {
        log.info("Start service findOperationBySourceAcc.");
        return service.findBySourceAcc(code);
    }

    /**
     * Service create operation.
     *
     * @param request {@link OperationRequest}
     * @return {@link OperationResponse}
     */
    @Operation(summary = "Create a new operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OperationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<OperationResponse> createOperation(@RequestBody final OperationRequest request) {
        log.info("Start service CreateOperation.");
        return service.save(request);
    }

    /**
     * Service update a operation.
     *
     * @param request {@link UpdateOperationRequest}
     * @return {@link OperationResponse}
     */
    @Operation(summary = "Update operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OperationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<OperationResponse> updateOperation(@RequestBody final UpdateOperationRequest request) {
        log.info("Start UpdateOperation.");
        return service.update(request);
    }

    /**
     * Service return all operations.
     *
     * @return {@link OperationResponse}
     */
    @Operation(summary = "Get all operations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OperationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<OperationResponse> findAllOperations() {
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
    @Operation(summary = "Delete operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @DeleteMapping("/{id}")
    public Mono<Void> deleteOperation(@PathVariable(value = "id") final String id) {
        return service.delete(id);
    }
}