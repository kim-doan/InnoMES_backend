package com.innomes.main.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * 서버 무중단 배포 서비스
 * 1. 실행중인 작업일 경우 작업중 종료되지않고 모두 완료된 후 종료되도록
 * 2. 30초 안에 종료되지 않으면 강제 작업 종료
 * @author kimdoan
 *
 */

public class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent>{	
	private static final int TIME_OUT = 30; // 30 Seconds
	
	private volatile Connector connector;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		this.connector.pause();
		Executor executor = this.connector.getProtocolHandler().getExecutor();
		
		if(executor instanceof ThreadPoolExecutor) {
			try {
				ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
				
				threadPoolExecutor.shutdown();
				
				if(!threadPoolExecutor.awaitTermination(TIME_OUT, TimeUnit.SECONDS) ) {
					logger.warn("Tomcat thread pool did not shutdown gracefully within " + TIME_OUT + "seconds. Proceeding with forceful shutdown");
					
					threadPoolExecutor.shutdownNow();
					
					if(!threadPoolExecutor.awaitTermination(TIME_OUT, TimeUnit.SECONDS)) {
						logger.error("Tomcat thread pool did not terminate");
					}
				} else {
					logger.info("Tomcat thread pool has been gracefully shutdown");
				}
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		
	}

	@Override
	public void customize(Connector connector) {
		this.connector = connector;
	}

}
