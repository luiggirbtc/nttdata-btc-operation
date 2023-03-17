package com.nttdata.btc.operation.app.model.response;

import com.nttdata.btc.operation.app.model.request.BaseRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Class response OperationResponse.
 *
 * @author lrs
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OperationResponse extends BaseRequest {
    @Schema(required = true, description = "Id operation", example = "120cf999662f294fc1234567")
    private String id_operation;

    @Schema(required = true, description = "Register date", example = "2023-03-11T21:58:49.101+00:00")
    private Date register_date;

    @Schema(required = true, description = "Status operation", example = "true")
    private Boolean status = false;

    @Schema(required = true, description = "Type description", example = "Depósito")
    private String typeDescription;

    @Schema(required = true, description = "Category description", example = "Pago crédito")
    private String categoryDescription;
}