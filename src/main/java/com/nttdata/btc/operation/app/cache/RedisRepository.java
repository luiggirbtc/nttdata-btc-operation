package com.nttdata.btc.operation.app.cache;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Class RedisRepository.
 *
 * @author lrs
 */
@Slf4j
@Repository
public class RedisRepository {
    @Autowired
    private RedisTemplate<String, RedisOperation> redisTemplate;


    public RedisOperation save(RedisOperation operation) {
        log.info("Register in Redis :: " + operation);
        try {
            log.info("Before save");
            redisTemplate.opsForValue()
                    .set(operation.getId_operation(), operation);
            log.info("After save");
        } catch (Exception e) {
            log.error("Error while save redis object :: " + e);
        }
        return operation;
    }

    public RedisOperation findById(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue()
                .get(key)).orElse(new RedisOperation());
    }

    public boolean delete() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
        return true;
    }
}