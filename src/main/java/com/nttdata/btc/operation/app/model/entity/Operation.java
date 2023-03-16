package com.nttdata.btc.operation.app.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Entity Operation.
 *
 * @author lrs
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Document(collection = "operation")
public class Operation {
    @Id
    private String id_operation;

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

    private Date register_date = new Date();

    private boolean status = true;

    /**
     * Constructor create a new operation.
     *
     * @param category       {@link Byte}
     * @param type           {@link Byte}
     * @param source_account {@link String}
     * @param target_account {@link String}
     * @param currency       {@link String}
     * @param amount         {@link BigDecimal}
     * @param description    {@link String}
     */
    public Operation(Byte category, Byte type, String source_account,
                     String target_account, String currency, BigDecimal amount, String description) {
        this.category = category;
        this.type = type;
        this.source_account = source_account;
        this.target_account = target_account;
        this.currency = currency;
        this.amount = amount;
        this.description = description;
    }
}