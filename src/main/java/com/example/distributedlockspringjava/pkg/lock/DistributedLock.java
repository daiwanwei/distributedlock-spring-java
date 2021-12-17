package com.example.distributedlockspringjava.pkg.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;


public abstract class DistributedLock<T> {
    private Logger logger = LoggerFactory.getLogger(DistributedLock.class);
    protected static final long DEFAULT_EXPIRE_UNUSED = 60000L;

    public void lock(T lockKey) {
        Lock lock = obtainLock(lockKey);
        lock.lock();
    }

    public boolean tryLock(T lockKey) {
        Lock lock = obtainLock(lockKey);
        return lock.tryLock();
    }

    public boolean tryLock(T lockKey, long seconds) {
        Lock lock = obtainLock(lockKey);
        try {
            return lock.tryLock(seconds, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    public void unlock(T lockKey) {
        try {
            Lock lock = obtainLock(lockKey);
            lock.unlock();
        } catch (Exception e) {
            logger.error("distributed lock exception:", lockKey, e);
        }
    }

    public abstract Lock obtainLock(T lockKey);
}
