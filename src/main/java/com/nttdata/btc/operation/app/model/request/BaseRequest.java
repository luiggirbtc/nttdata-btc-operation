package com.nttdata.btc.operation.app.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseRequest {
    /*
     * 1 - Transacción, 2 - Transferencia, 3 - Movimiento/Consumo de tarjeta
     */
    @NotNull(message = "Is mandatory")
    @NotEmpty(message = "Not be empty")
    @Schema(required = true, description = "Category operation", example = "1 - Transacción | 2 - Transferencia | 3 - Movimiento/Consumo de tarjeta")
    private Byte category;

    /*
     * bancario(depósitos, retiro), credito(pago)
     * 1 = Depósito, 2= Retiro, 3 = Pago crédito
     */
    @NotNull(message = "Is mandatory")
    @NotEmpty(message = "Not be empty")
    @Schema(required = true, description = "Type operation", example = "1 = Depósito | 2= Retiro | 3 = Pago crédito")
    private Byte type;

    @Schema(required = false, description = "Description operation", example = "Pago factura.")
    private String description;

    @NotNull(message = "Is mandatory")
    @NotEmpty(message = "Not be empty")
    @Schema(required = true, description = "Code source account", example = "640cc29c60650d1637e040a90")
    private String source_account;

    @NotNull(message = "Is mandatory")
    @NotEmpty(message = "Not be empty")
    @Schema(required = true, description = "Code target account", example = "123-1234567-0-00")
    private String target_account;

    @NotNull(message = "Is mandatory")
    @NotEmpty(message = "Not be empty")
    @Schema(required = true, description = "Currency operation", example = "PEN | USD")
    private String currency;

    @NotNull(message = "Is mandatory")
    @Schema(required = true, description = "Amount operation", example = "250.0")
    private BigDecimal amount;
}