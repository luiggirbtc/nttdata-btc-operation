package com.nttdata.btc.operation.app.model.response;

import com.nttdata.btc.operation.app.model.request.BaseRequest;
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
    private String id_operation;
    private Date register_date;
    private Boolean status = false;

    private String typeDescription;
    private String categoryDescription;
}