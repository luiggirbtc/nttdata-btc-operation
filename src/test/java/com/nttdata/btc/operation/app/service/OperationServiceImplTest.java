package com.nttdata.btc.operation.app.service;

import com.nttdata.btc.operation.app.cache.RedisOperation;
import com.nttdata.btc.operation.app.cache.RedisRepository;
import com.nttdata.btc.operation.app.model.entity.Operation;
import com.nttdata.btc.operation.app.model.request.OperationRequest;
import com.nttdata.btc.operation.app.model.request.UpdateOperationRequest;
import com.nttdata.btc.operation.app.model.response.OperationResponse;
import com.nttdata.btc.operation.app.repository.OperationRepository;
import com.nttdata.btc.operation.app.service.impl.OperationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationServiceImplTest {

    @Mock
    OperationRepository repository;

    @Mock
    RedisRepository redis;

    @InjectMocks
    OperationServiceImpl service;

    List<Operation> listOperation = new ArrayList<>();

    static final String ID_OPERATION = "640cf999662f294fc9169737";
    static final String SOURCE_ACCOUNT_CODE = "640cc29c60650d1637e040a9";

    @BeforeEach
    private void setUp() {
        Operation operation = new Operation();
        operation.setId_operation(ID_OPERATION);
        operation.setCategory(Byte.parseByte("2"));
        operation.setType(Byte.parseByte("1"));
        operation.setDescription("Pago facturas diciembre");
        operation.setSource_account(SOURCE_ACCOUNT_CODE);
        operation.setTarget_account("193-1853964-0-30");
        operation.setCurrency("PEN");
        operation.setAmount(new BigDecimal("2345.0"));
        operation.setRegister_date(new Date());
        operation.setStatus(true);

        listOperation.add(operation);
    }

    @Test
    @DisplayName("Return all operations")
    void testFindAllOperations() {
        when(repository.findAll()).thenReturn(Flux.fromIterable(listOperation));
        when(redis.save(any())).thenReturn(new RedisOperation());
        Flux<OperationResponse> result = service.findAll();

        assertEquals(result.blockFirst().getId_operation(), listOperation.get(0).getId_operation());
    }

    @Test
    @DisplayName("Return error while get all operations")
    void testFindAllOperationsErrorRedis() {
        when(repository.findAll()).thenReturn(Flux.fromIterable(listOperation));
        when(redis.save(any())).thenReturn(null);
        Flux<OperationResponse> result = service.findAll();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, result::blockFirst);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

    @Test
    @DisplayName("Return operations by id")
    void testFindById() {
        when(repository.findById(anyString())).thenReturn(Mono.just(listOperation.get(0)));
        when(redis.findById(anyString())).thenReturn(new RedisOperation());
        Mono<OperationResponse> result = service.findById(ID_OPERATION);

        assertEquals(ID_OPERATION, result.block().getId_operation());
    }

    @Test
    @DisplayName("Return operations by source account")
    void testFindBySourceAcc() {
        when(repository.findAll()).thenReturn(Flux.fromIterable(listOperation));

        Flux<OperationResponse> result = service.findBySourceAcc(SOURCE_ACCOUNT_CODE);

        assertEquals(SOURCE_ACCOUNT_CODE, result.blockFirst().getSource_account());
    }

    @Test
    @DisplayName("Create a new operation")
    void testSave() {
        when(repository.save(any())).thenReturn(Mono.just(listOperation.get(0)));

        OperationRequest request = new OperationRequest();
        request.setCategory(Byte.parseByte("2"));
        request.setType(Byte.parseByte("1"));
        request.setDescription("Pago facturas diciembre");
        request.setSource_account(SOURCE_ACCOUNT_CODE);
        request.setTarget_account("193-1853964-0-30");
        request.setCurrency("PEN");
        request.setAmount(new BigDecimal("2345.0"));

        Mono<OperationResponse> result = service.save(request);

        assertEquals(SOURCE_ACCOUNT_CODE, result.block().getSource_account());
    }

    @Test
    @DisplayName("Delete operation")
    void testDelete() {
        Operation operation = listOperation.get(0);

        when(repository.findById(anyString())).thenReturn(Mono.just(operation));
        when(repository.save(any())).thenReturn(Mono.just(operation));

        Mono<Boolean> result = service.delete(ID_OPERATION).thenReturn(Boolean.TRUE);

        assertTrue(result.block());
    }

    @Test
    @DisplayName("Update operation")
    void testUpdate() {
        Operation operation = listOperation.get(0);
        when(repository.findById(anyString())).thenReturn(Mono.just(operation));
        when(repository.save(any())).thenReturn(Mono.just(operation));

        UpdateOperationRequest request = new UpdateOperationRequest();
        request.setId_operation(ID_OPERATION);
        request.setCategory(Byte.parseByte("2"));
        request.setType(Byte.parseByte("1"));
        request.setDescription("Pago facturas diciembre updated");
        request.setSource_account(SOURCE_ACCOUNT_CODE);
        request.setTarget_account("193-1853964-0-30");
        request.setCurrency("PEN");
        request.setAmount(new BigDecimal("2345.0"));

        Mono<OperationResponse> result = service.update(request);

        assertEquals(result.block().getDescription(), request.getDescription());
    }
}