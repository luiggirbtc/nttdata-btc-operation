package com.nttdata.btc.operation.app.util.mappers;

import com.nttdata.btc.operation.app.cache.RedisOperation;
import com.nttdata.btc.operation.app.model.entity.Operation;
import com.nttdata.btc.operation.app.model.response.OperationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import static com.nttdata.btc.operation.app.util.enums.CategoryOperationEnum.findOperationCategory;
import static com.nttdata.btc.operation.app.util.enums.TypeOperationEnum.findOperationType;

/**
 * Class interface mapper OperationResponseMapper.
 *
 * @author lrs
 */
@Mapper
public interface OperationResponseMapper {

    @Mapping(target = "id_operation", source = "id_operation")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "categoryDescription", target = "categoryDescription")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "typeDescription", target = "typeDescription")
    @Mapping(source = "source_account", target = "source_account")
    @Mapping(source = "target_account", target = "target_account")
    @Mapping(source = "currency", target = "currency")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "register_date", target = "register_date")
    @Mapping(source = "status", target = "status")
    OperationResponse redisToResponse(RedisOperation response);

    @Mapping(target = "id_operation", source = "id_operation")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "categoryDescription", target = "categoryDescription")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "typeDescription", target = "typeDescription")
    @Mapping(source = "source_account", target = "source_account")
    @Mapping(source = "target_account", target = "target_account")
    @Mapping(source = "currency", target = "currency")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "register_date", target = "register_date")
    @Mapping(source = "status", target = "status")
    RedisOperation toRedis(OperationResponse response);

    @Mapping(target = "id_operation", source = "id_operation")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "entity", target = "categoryDescription", qualifiedByName = "findOCategory")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "entity", target = "typeDescription", qualifiedByName = "findOType")
    @Mapping(source = "source_account", target = "source_account")
    @Mapping(source = "target_account", target = "target_account")
    @Mapping(source = "currency", target = "currency")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "register_date", target = "register_date")
    @Mapping(source = "status", target = "status")
    OperationResponse toResponse(Operation entity);

    @Named("findOCategory")
    static String findOCategory(Operation entity) {
        return findOperationCategory(entity.getCategory().intValue()).getDescription();
    }

    @Named("findOType")
    static String findOType(Operation entity) {
        return findOperationType(entity.getType().intValue()).getDescription();
    }
}