package com.trevzhang.demo.test;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;

/**
 * @author zhangchunguang.zcg
 * @since 2022/9/28 11:47 AM
 */
public class EventBusTest {
    @Test
    public void test1() {
        // 定义一个EventBus对象，这里的Joker是该对象的id
        EventBus eventBus = new EventBus("Joker");
        // 向上述EventBus对象中注册一个监听对象
        eventBus.register(new EventListener("1"));
        // 使用EventBus发布一个事件，该事件会给通知到所有注册的监听者
        eventBus.post(new Event("Hello every listener, joke begins..."));
    }

    // 事件，监听者监听的事件的包装对象
    public static class Event {
        public String message;
        Event(String message) {
            this.message = message;
        }
    }

    // 监听者
    public static class EventListener {
        private String name;
        // 监听的方法，必须使用注解声明，且只能有一个参数，实际触发一个事件的时候会根据参数类型触发方法
        @Subscribe
        public void listen(Event event) {
            System.out.println(String.format("Event listener %s received: ", name) + event.message);
        }

        public EventListener(String name) {
            this.name = name;
        }
    }

}
