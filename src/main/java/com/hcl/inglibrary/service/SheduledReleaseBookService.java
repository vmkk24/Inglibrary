package com.hcl.inglibrary.service;

import com.hcl.inglibrary.dto.SchedulderResponse;

/**
 * 
 * @author sharath vemperala
 *
 */

public interface SheduledReleaseBookService {

	public SchedulderResponse autoReleaseBook();

	public SchedulderResponse autoHoldBookForPreRelease();
}
