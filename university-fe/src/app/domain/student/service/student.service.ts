import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";


@Injectable({
    providedIn: 'root'
})
export class StudentService {

    /** base url backend */
    private baseUrl="http://localhost:8080/api/v1/student";

    constructor(private http:HttpClient){}

    public doCallListStudent():Observable<any>{
        return this.http.get(`${this.baseUrl}/list`);
    }

    public doCallCreateStudent(student:object):Observable<object>{
        return this.http.post(`${this.baseUrl}/create`, student);
    }

    public doCallDetailStudentById(id:number):Observable<any>{
        return this.http.get(`${this.baseUrl}/detail/`+id);
    }

    public doCallUpdateStudent(student:object):Observable<object>{
        return this.http.post(`${this.baseUrl}/update`, student);
    }
}