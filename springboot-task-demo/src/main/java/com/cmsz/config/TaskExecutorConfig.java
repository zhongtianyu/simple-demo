package com.cmsz.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程池
 * 
 * <p>从实际来看，线程数会维持在配置的corePoolSize，而不会增加，原因在于：<br>
 * 	没有配置线程阻塞队列长度queueCapacity，该队列长度默认取值<code>Integer.MAX_VALUE</code>。<br>
 * 	当线程数增长到corePoolSize后，其余线程都在阻塞队列等待，而不会新开辟线程，所以最大线程数配置基本不会用到，除非待执行任务数超过了<code>Integer.MAX_VALUE</code>
 * @author quanyq
 * @date: 2018年11月26日 上午8:49:45 
 * @version: V1.0
 */
@Slf4j
@Configuration
@EnableAsync
public class TaskExecutorConfig {

	private static final int DEFAULT_CONCURRENT_THREADS;

	private static final int DEFAULT_MAX_CONCURRENT_THREADS;

	static {
		DEFAULT_CONCURRENT_THREADS = Runtime.getRuntime().availableProcessors() * 2;
		DEFAULT_MAX_CONCURRENT_THREADS = Runtime.getRuntime().availableProcessors() * 4;
	}

	/** 最大线程数 */
	@Value("${clearingRedis-service.max-concurrence:32}")
    private int maxConcurrent;

	/**
	 * 核心线程数
	 */
	@Value("${datastore-service.fileLinePool-size:3}")
	private int fileLinePoolSize;

	/**
	 * 线程池等待队列长度
	 */
	@Value("${datastore-service.fileLinePool-WaitSize:3}")
	private int fileLinePoolWaitSize;

	/**
	 * 核心线程数
	 */
	@Value("${datastore-service.tablePool-size:3}")
	private int tablePoolSize;

	/**
	 * 线程池等待队列长度
	 */
	@Value("${datastore-service.tablePool-WaitSize:20}")
	private int tablePoolWaitSize;

	@Bean("importTaskExecutor")
    public Executor importTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int coreConcurrentThread = tablePoolSize > 0 ? tablePoolSize : DEFAULT_CONCURRENT_THREADS;
		int maxConcurrentThread = maxConcurrent > 0 ? maxConcurrent : DEFAULT_MAX_CONCURRENT_THREADS;
        executor.setCorePoolSize(coreConcurrentThread);
        executor.setMaxPoolSize(maxConcurrentThread);
		executor.setQueueCapacity(tablePoolWaitSize);
        executor.setThreadNamePrefix("DataStore-");
		executor.setRejectedExecutionHandler(new RejectedExecutionHandlerImpl());
        executor.initialize();
		// 使用阿里的组件解决线程切换时转移MDC属性的问题
		log.info("将使用{}个核心线程进行文件处理！", coreConcurrentThread);
        return executor.getThreadPoolExecutor();
    }

	@Bean("fileLineTaskExecutor")
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		int coreConcurrentThread = fileLinePoolSize > 0 ? fileLinePoolSize : DEFAULT_CONCURRENT_THREADS;
		int maxConcurrentThread = maxConcurrent > 0 ? maxConcurrent : DEFAULT_MAX_CONCURRENT_THREADS;
		executor.setCorePoolSize(coreConcurrentThread);
		executor.setMaxPoolSize(maxConcurrentThread);
		executor.setQueueCapacity(fileLinePoolWaitSize);
		executor.setThreadNamePrefix("DataStore-");
		executor.setRejectedExecutionHandler(new RejectedExecutionHandlerImpl());
		executor.initialize();
		// 使用阿里的组件解决线程切换时转移MDC属性的问题
		log.info("将使用{}个核心线程进行文件处理！", coreConcurrentThread);
		return executor.getThreadPoolExecutor();
	}

	@Bean("schedulingTaskScheduler")
	public TaskScheduler scheduledExecutorService() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		int coreConcurrentThread = tablePoolSize > 0 ? tablePoolSize : DEFAULT_CONCURRENT_THREADS;
		scheduler.setPoolSize(coreConcurrentThread);
		scheduler.setThreadNamePrefix("scheduled-thread-");
		return scheduler;
	}

	/**
	 * 当队列堆积满的时候，不会丢弃任务，而是进行重试
	 */
	public static class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {
		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			log.info("任务溢出，重新将任务添加到执行器执行！");
			executor.execute(r);
		}
	}
}
