package com.hcl.inglibrary.schedulder;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class ScheduledTasks {

	
	
	@Scheduled(fixedRate = 10000)
	public void scheduleTaskWithFixedRate() {
		System.out.println("break");

	}

}
