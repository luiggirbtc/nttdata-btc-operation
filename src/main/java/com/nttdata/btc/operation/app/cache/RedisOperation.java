package com.nttdata.btc.operation.app.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Class redisOperation
 *
 * @author lrs
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RedisOperation implements Serializable {
    private String id_operation;
    private Date register_date;
    private Boolean status = false;
    private String typeDescription;
    private String categoryDescription;
    private Byte category;
    private Byte type;
    private String description;
    private String source_account;
    private String target_account;
    private String currency;
    private BigDecimal amount;
}