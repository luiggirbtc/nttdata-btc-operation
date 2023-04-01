package com.nttdata.btc.operation.app.controller;

import com.nttdata.btc.operation.app.model.request.OperationRequest;
import com.nttdata.btc.operation.app.model.request.UpdateOperationRequest;
import com.nttdata.btc.operation.app.model.response.OperationResponse;
import com.nttdata.btc.operation.app.service.OperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationControllerTest {
    @InjectMocks
    OperationController controller;

    @Mock
    OperationService service;

    List<OperationResponse> listOperation = new ArrayList<>();

    @BeforeEach
    private void setUp() {
        OperationResponse operationActive = new OperationResponse();
        operationActive.setId_operation("640cf999662f294fc9169737");
        operationActive.setCategory(Byte.parseByte("2"));
        operationActive.setType(Byte.parseByte("1"));
        operationActive.setDescription("Pago facturas diciembre");
        operationActive.setSource_account("640cc29c60650d1637e040a9");
        operationActive.setTarget_account("193-1853964-0-30");
        operationActive.setCurrency("PEN");
        operationActive.setAmount(new BigDecimal("2345.0"));
        operationActive.setRegister_date(new Date());
        operationActive.setStatus(true);

        listOperation.add(operationActive);
    }

    @Test
    @DisplayName("Return all operations")
    void testFindAllOperations() {
        when(service.findAll()).thenReturn(Flux.fromIterable(listOperation));

        Flux<OperationResponse> result = controller.findAllOperations();

        assertEquals(result.blockFirst().getId_operation(), listOperation.get(0).getId_operation());
    }

    @Test
    void testCreateOperation() {
        OperationResponse response = listOperation.get(0);

        OperationRequest request = new OperationRequest();
        request.setCategory(response.getCategory());
        request.setType(response.getType());
        request.setDescription(response.getDescription());
        request.setSource_account(response.getSource_account());
        request.setTarget_account(response.getTarget_account());
        request.setCurrency(response.getCurrency());
        request.setAmount(response.getAmount());

        when(service.save(request)).thenReturn(Mono.just(response));

        Mono<OperationResponse> result = controller.createOperation(request);

        assertEquals(result.block().getTarget_account(), response.getTarget_account());
    }

    @Test
    void testUpdateOperation() {
        UpdateOperationRequest updateRequest = new UpdateOperationRequest();
        updateRequest.setId_operation("640cf999662f294fc9169737");
        updateRequest.setCategory(Byte.parseByte("2"));
        updateRequest.setType(Byte.parseByte("1"));
        updateRequest.setDescription("Pago facturas diciembre actualizado");
        updateRequest.setSource_account("640cc29c60650d1637e040a9");
        updateRequest.setTarget_account("193-1853964-0-30");
        updateRequest.setCurrency("PEN");
        updateRequest.setAmount(new BigDecimal("2345.0"));

        OperationResponse responseUpdated = listOperation.get(0);
        responseUpdated.setDescription(updateRequest.getDescription());

        when(service.update(updateRequest)).thenReturn(Mono.just(responseUpdated));

        Mono<OperationResponse> result = controller.updateOperation(updateRequest);

        assertEquals(result.block().getDescription(), responseUpdated.getDescription());
    }

    @Test
    void testFindOperationBySourceAcc() {
        String sourceAccount = "640cc29c60650d1637e040a9";
        when(service.findBySourceAcc(anyString())).thenReturn(Flux.fromIterable(listOperation));

        Flux<OperationResponse> result = controller.findOperationBySourceAcc(sourceAccount);

        assertEquals(result.blockFirst().getSource_account(), listOperation.get(0).getSource_account());
    }

    @Test
    void testFindOperationById() {
        String id = "640cf999662f294fc9169737";
        when(service.findById(anyString())).thenReturn(Mono.just(listOperation.get(0)));

        Mono<OperationResponse> result = controller.findOperationById(id);

        assertEquals(result.block().getSource_account(), listOperation.get(0).getSource_account());
    }

    @Test
    void testDeleteOperation() {
        String id = "640cf999662f294fc9169737";

        when(service.delete(anyString())).thenReturn(Mono.empty());

        Mono<Boolean> result = controller.deleteOperation(id).thenReturn(Boolean.TRUE);

        assertTrue(result.block());
    }
}