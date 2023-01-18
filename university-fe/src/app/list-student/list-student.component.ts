import { Student } from './../domain/student/model/student';
import { Observable } from 'rxjs';
import { StudentService } from './../domain/student/service/student.service';
import { OnInit, Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
    selector: 'app-list-student',
    templateUrl: './list-student.component.html'
})
export class ListStudentComponent implements OnInit{
   
    @Input() selectedIdStudent:number;
    @Output() onSelectedIdStudent = new EventEmitter<any>();

    students: Student[];
    constructor(private studentService:StudentService){}

    titlePage = 'List Student';
    ngOnInit(): void {
        console.log("this is page list student");
        this.listStudent()
    }

    listStudent(){
        this.studentService.doCallListStudent().subscribe(data=>{
            this.students=data.data;
            console.log("students="+JSON.stringify(this.students));
        });
    }

    detailIdStudent(id:number){
        console.log("id selected->"+id);
        this.selectedIdStudent = id;
        this.onSelectedIdStudent.emit(this.selectedIdStudent);
    }
}