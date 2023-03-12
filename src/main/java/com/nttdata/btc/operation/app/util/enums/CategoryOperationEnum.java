package com.nttdata.btc.operation.app.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * EnumClass CategoryOperationEnum.
 *
 * @author lrs
 */
@AllArgsConstructor
@Getter
public enum CategoryOperationEnum {
    TRANSACCION(1, "Transacción"),
    PAGO_CREDITO(2, "Pago crédito"),
    CONSUMO(3, "Consumo");
    private Integer code;
    private String description;

    /**
     * Method search CategoryOperationEnum by code.
     *
     * @param code {@link Integer}
     * @return {@link CategoryOperationEnum}
     */
    public static CategoryOperationEnum findOperationCategory(Integer code) {
        return Arrays.stream(CategoryOperationEnum.values())
                .filter(e -> e.code.equals(code)).findFirst()
                .orElse(null);
    }
}