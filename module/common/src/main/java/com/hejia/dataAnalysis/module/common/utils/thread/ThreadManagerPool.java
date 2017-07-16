package com.hejia.dataAnalysis.module.common.utils.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import com.hejia.dataAnalysis.module.common.ModuleConfig;

/**
 * @Description: 线程池
 * @author: chenyongqiang
 * @Date: 2015年5月6日
 * @version: 1.0
 */
public class ThreadManagerPool {
	
	private static ThreadPoolExecutor threadPoolExecutor;
	
	private static CallerRunsPolicy crp = new ThreadPoolExecutor.CallerRunsPolicy();
	
	static {
		
		threadPoolExecutor = new ThreadPoolExecutor(ModuleConfig.getInt("thread_corePoolSize"),
				ModuleConfig.getInt("thread_maxnumPoolSize"), ModuleConfig.getInt("thread_keepAliveTime"), TimeUnit.SECONDS,
				new ArrayBlockingQueue(ModuleConfig.getInt("thread_sequenceLength")), crp);
	}

	/**
	 * @Definition: 将一个线程类增加到线程池中，并且进行运行 
	 * @author: chenyongqiang
	 * @Date: 2015年5月6日
	 * @param runnable
	 */
	public static void addThread(Runnable runnable) {
		threadPoolExecutor.execute(runnable);
	}
}
