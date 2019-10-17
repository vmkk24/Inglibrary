package com.hcl.inglibrary.schedulder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hcl.inglibrary.service.LoginServiceImpl;
import com.hcl.inglibrary.service.SheduledReleaseBookService;

/**
 * 
 * @author sharath vemperala
 *
 */

@Component
public class ScheduledTasks {
	
	/*
	 * This will run the schedulder every day once and this will automatically
	 * release and issues the books bases on due date
	 */
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private SheduledReleaseBookService scheduledReleaseBookService;

	@Scheduled(fixedRate = 100000)
	public void scheduleTaskWithFixedRate() {
		logger.info(":: Enter into ScheduledTasks--------::scheduleTaskWithFixedRate()");
		scheduledReleaseBookService.autoReleaseBook();
		scheduledReleaseBookService.autoHoldBookForPreRelease();

	}

}
