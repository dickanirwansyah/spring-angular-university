package com.rnd.university.universitybe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rnd.university.universitybe.base.BasePageableRequest;
import com.rnd.university.universitybe.model.EmptyRequest;
import com.rnd.university.universitybe.model.FacultyRequest;
import com.rnd.university.universitybe.model.GetIdRequest;
import com.rnd.university.universitybe.model.RestResponse;
import com.rnd.university.universitybe.service.faculty.CreateFacultyService;
import com.rnd.university.universitybe.service.faculty.DeleteFacultyService;
import com.rnd.university.universitybe.service.faculty.ListFacultyService;
import com.rnd.university.universitybe.service.faculty.SearchFacultyService;
import com.rnd.university.universitybe.service.faculty.UpdateFacultyService;

@RestController
@RequestMapping(value = "/api/v1/faculty")
public class FacultyController {

	@Autowired
	private CreateFacultyService createFacultyService;
	
	@Autowired
	private UpdateFacultyService updateFacultyService;
	
	@Autowired
	private ListFacultyService listFacultyService;
	
	@Autowired
	private DeleteFacultyService deleteFacultyService;
	
	@Autowired
	private SearchFacultyService searchFacultyService;
	
	
	@PostMapping(value = "/create")
	public ResponseEntity<RestResponse> create(@RequestBody FacultyRequest request){
		return ResponseEntity.ok(RestResponse
				.success(this.createFacultyService.excute(request)));
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<RestResponse> update(@PathVariable("id")Long id, 
			@RequestBody FacultyRequest request){
		request.setFacultyId(id);
		return ResponseEntity.ok(RestResponse
				.success(this.updateFacultyService.excute(request)));
	}
	
	/** dropdown faculty **/
	@GetMapping(value = "/list")
	public ResponseEntity<RestResponse> list(){
		return ResponseEntity.ok(RestResponse.
				success(listFacultyService.excute(new EmptyRequest())));
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<RestResponse> delete(@PathVariable("id")Long id){
		return ResponseEntity.ok(RestResponse
				.success(deleteFacultyService.excute(GetIdRequest
						.builder()
						.id(id)
						.build())));
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<RestResponse> search(Pageable pageable){
		return ResponseEntity.ok(RestResponse
				.success(searchFacultyService.excute(BasePageableRequest
						.builder()
						.pageable(pageable)
						.build())));
	}
}
