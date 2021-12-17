package com.example.distributedlockspringjava.pkg.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

@Component
public class HelloLock extends DistributedLock<String> {
    private Logger logger = LoggerFactory.getLogger(HelloLock.class);
    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @Override
    public void unlock(String lockKey) {
        try {
            Lock lock = obtainLock(lockKey);
            lock.unlock();
            redisLockRegistry.expireUnusedOlderThan(this.DEFAULT_EXPIRE_UNUSED);
        } catch (Exception e) {
            logger.error("distributed lock exception:", lockKey, e);
        }
    }

    public Lock obtainLock(String lockKey){
        return this.redisLockRegistry.obtain(lockKey);
    };
}
