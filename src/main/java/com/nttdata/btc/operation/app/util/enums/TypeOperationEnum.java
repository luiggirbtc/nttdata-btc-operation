package com.nttdata.btc.operation.app.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * EnumClass TypeOperationEnum.
 *
 * @author lrs
 */
@AllArgsConstructor
@Getter
public enum TypeOperationEnum {
    DEPOSITO(1, "Depósito"),
    RETIRO(2, "Retiro"),
    PAGO_CREDITO(3, "Pago crédito");
    private Integer code;
    private String description;

    /**
     * Method search TypeOperationEnum by code.
     *
     * @param code {@link Integer}
     * @return {@link TypeOperationEnum}
     */
    public static TypeOperationEnum findOperationType(Integer code) {
        return Arrays.stream(TypeOperationEnum.values())
                .filter(e -> e.code.equals(code)).findFirst()
                .orElse(null);
    }
}