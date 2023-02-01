package com.rnd.university.universitybe.service.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rnd.university.universitybe.base.BasePageableRequest;
import com.rnd.university.universitybe.base.BasePageableResponse;
import com.rnd.university.universitybe.base.BaseService;
import com.rnd.university.universitybe.repository.FacultyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SearchFacultyService implements BaseService<BasePageableRequest, BasePageableResponse>{
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	@Override
	public BasePageableResponse excute(BasePageableRequest request) {
		log.info("request ={} ",request.getPageable().toString());
		return BasePageableResponse.builder()
				.page(facultyRepository.getFacultyPage(request.getPageable()))
				.build();
	}

}
