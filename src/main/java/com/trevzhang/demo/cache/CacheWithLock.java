package com.trevzhang.demo.cache;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

/**
 * @author Haruki
 * @since 2024/11/26 14:23
 */
@Slf4j
public class CacheWithLock {
    private static final String MUTEX_KEY = "mutex:key";
    private static final int LOCK_EXPIRE_TIME = 5000;
    private static final int MAX_RETRIES = 10;
    private static final int DATA_CACHE_EXPIRE_TIME = 120000;
    private final Jedis jedis;

    public CacheWithLock() {
        this.jedis = new Jedis("127.0.0.1", 6379);
    }

    public String getData(String key) {
        return getDataWithRetries(key, 0);
    }

    private String getDataWithRetries(String key, int retryCount) {
        String value = jedis.get(key);
        if (value != null) {
            log.info("Cache hit for key: {}", key);
            return value;
        }

        long lockAcquired = jedis.setnx(MUTEX_KEY, "locked");
        if (lockAcquired == 1) {
            log.info("Lock acquired for key: {}", key);
            try {
                jedis.expire(MUTEX_KEY, LOCK_EXPIRE_TIME / 1000);
                value = loadDataFromDatabase(key);
                jedis.set(key, value, new SetParams().ex(DATA_CACHE_EXPIRE_TIME / 1000));
                log.info("Data loaded and cached for key: {}", key);
            } finally {
                jedis.del(MUTEX_KEY);
                log.info("Lock released for key: {}", key);
            }
        } else {
            log.warn("Failed to acquire lock for key: {}. Retry count: {}", key, retryCount);
            if (retryCount < MAX_RETRIES) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return getDataWithRetries(key, retryCount + 1);
            } else {
                log.error("Reached maximum retry limit for key: {}", key);
                return null;
            }
        }

        return value;
    }

    private String loadDataFromDatabase(String key) {
        return "data from database for key: " + key;
    }

    public static void main(String[] args) {
        CacheWithLock cacheWithLock = new CacheWithLock();
        String data = cacheWithLock.getData("exampleKey");
        System.out.println(data);
    }
}

