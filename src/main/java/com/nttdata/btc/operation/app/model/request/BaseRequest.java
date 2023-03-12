package com.nttdata.btc.operation.app.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Class BaseRequest.
 *
 * @author lrs
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BaseRequest {
    /*
     * 1 - Transacción, 2 - Transferencia, 3 - Movimiento/Consumo de tarjeta
     */
    private Byte category;

    /*
     * bancario(depósitos, retiro), credito(pago)
     * 1 = Depósito, 2= Retiro, 3 = Pago
     */
    private Byte type;

    private String description;

    private String source_account;

    private String target_account;

    private String currency;

    private BigDecimal amount;
}