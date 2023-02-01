package com.rnd.university.universitybe.base;

import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasePageableRequest extends BaseRequest{

	private Pageable pageable;
	
}
