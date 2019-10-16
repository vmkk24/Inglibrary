package com.hcl.inglibrary.service;

import com.hcl.inglibrary.dto.RegisterRequestDto;
import com.hcl.inglibrary.dto.RegisterResponseDto;

public interface RegisterService {

	RegisterResponseDto register(RegisterRequestDto registerRequestDto);

}
