package com.nttdata.btc.operation.app.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class UpdateAccountRequest.
 *
 * @author lrs
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateOperationRequest extends BaseRequest {
    private String id_operation;
}