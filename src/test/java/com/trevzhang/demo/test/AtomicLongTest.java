package com.trevzhang.demo.test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * @author zhangchunguang.zcg
 * @since 2023/1/3 2:18 PM
 */
@Slf4j
public class AtomicLongTest {

    public static void main(String[] args) {
        Count count = new Count();
        ExecutorService service = Executors.newCachedThreadPool();
        // 多线程并发CAS 1000次
        for (int i = 0; i < 1000; i++) {
            service.execute(count::increase);
        }
        service.shutdown();
        try {
            // 同步等待所有线程结束
            boolean sync = service.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("End count :{}", count.getCount());
    }

    @Data
    static class Count {
        private Long count = 0L;

        public void increase() {
            log.info("count {}", count++);
        }
    }
}