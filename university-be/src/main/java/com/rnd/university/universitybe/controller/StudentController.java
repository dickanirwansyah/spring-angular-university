package com.rnd.university.universitybe.controller;

import com.rnd.university.universitybe.model.EmptyRequest;
import com.rnd.university.universitybe.model.GetIdRequest;
import com.rnd.university.universitybe.model.RestResponse;
import com.rnd.university.universitybe.model.StudentRequest;
import com.rnd.university.universitybe.service.student.CreateStudentService;
import com.rnd.university.universitybe.service.student.DeleteStudentService;
import com.rnd.university.universitybe.service.student.DetailStudentService;
import com.rnd.university.universitybe.service.student.ListStudentService;
import com.rnd.university.universitybe.service.student.UpdateStudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/student")
public class StudentController {

    @Autowired
    private CreateStudentService createStudentService;

    @Autowired
    private ListStudentService listStudentService;

    @Autowired
    private DetailStudentService detailStudentService;

    @Autowired
    private UpdateStudentService updateStudentService;
    
    @Autowired
    private DeleteStudentService deleteStudentService;
    
    @PostMapping(value = "/create")
    public ResponseEntity<RestResponse> create(@RequestBody StudentRequest request){
        return ResponseEntity.ok(RestResponse
                .success(createStudentService.
                        excute(request)));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<RestResponse> list(){
        return ResponseEntity.ok(RestResponse
                .success(listStudentService
                        .excute(new EmptyRequest()).getStudentResponses()));
    }

    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<RestResponse> detail(@PathVariable("id")Long id){
        GetIdRequest request = new GetIdRequest();
        request.setId(id);
        return ResponseEntity.ok(RestResponse
                .success(detailStudentService.excute(request)));
    }
    
    @PostMapping(value = "/update")
    public ResponseEntity<RestResponse> update(@RequestBody StudentRequest request){
    	return ResponseEntity.ok(RestResponse
    			.success(this.updateStudentService.excute(request)));
    }
    
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<RestResponse> delete(@PathVariable("id")Long id){
    	return ResponseEntity.ok(RestResponse
    			.success(this.deleteStudentService.excute(GetIdRequest
    					.builder()
    					.id(id)
    					.build())));
    }
}
