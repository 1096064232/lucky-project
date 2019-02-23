package com.lucky.common.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

@Configuration
@ComponentScan("com.lucky")
@EnableAsync // 利用@EnableAsync注解开启异步任务支持
public class AsyncTaskConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() { // 配置类实现AsyncConfigurer接口并重写 getAsyncExecutor 方法,并返回一个 ThreadPoolTaskExecutor,这样我们就获得了一个线程池 taskExecutor
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(300);//当前线程数
        threadPool.setMaxPoolSize(500);// 最大线程数
        threadPool.setQueueCapacity(100);//线程池所使用的缓冲队列
        threadPool.setWaitForTasksToCompleteOnShutdown(true);//等待任务在关机时完成--表明等待所有线程执行完
        threadPool.setAwaitTerminationSeconds(60 * 15);// 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
        threadPool.setThreadNamePrefix("MyAsync-");//  线程名称前缀
        threadPool.initialize(); // 初始化
        System.out.println("--------------------------》》》开启异常线程池");
        return threadPool;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncExceptionHandler();
    }

    /**
     * 自定义异常处理类
     *
     * @author hry
     */
    class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        private Logger logger = LoggerFactory.getLogger(getClass());

        //手动处理捕获的异常
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
            System.out.println("-------------》》》捕获线程异常信息");
            logger.info("Exception message - " + throwable.getMessage());
            logger.info("Method name - " + method.getName());
            for (Object param : obj) {
                logger.info("Parameter value - " + param);
            }
        }
    }
}


