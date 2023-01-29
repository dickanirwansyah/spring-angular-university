import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({providedIn: 'root'})
export class FacultyService {

    /** base url backend */
    private baseUrl="http://localhost:8080/api/v1/faculty";

    constructor(private http:HttpClient){}

    public doCallListFaculty():Observable<any>{
        return this.http.get(`${this.baseUrl}/list`);
    }
    
}