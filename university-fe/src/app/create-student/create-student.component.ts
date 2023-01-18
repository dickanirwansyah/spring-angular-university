import { Student } from './../domain/student/model/student';
import { MatSnackBar } from '@angular/material';
import { StudentService } from './../domain/student/service/student.service';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
    selector: 'app-create-student',
    templateUrl: './create-student.component.html'
})
export class CreateStudentComponent implements OnInit{

    constructor(private studentService:StudentService, 
        private snackBar: MatSnackBar){}

    student:Student=new Student();
    subitted=false;
    model: NgbDateStruct;
    parseDate:string|null;
    titlePage = 'Create Student';
    readonly DELIMITER = "-";
    selectedIdStudent: number;
    isEdit:boolean;
    currentBirthDateSelected:string|null;

    ngOnInit(): void {
        console.log("this is page create student..")
        this.subitted=false;
        console.log("detail id selected student -> "+this.selectedIdStudent);
        this.isEdit=false;
        this.selectedIdStudent=0;
    }

    parse(value:string):NgbDateStruct | null {
        if (value){
            let date = value.split(this.DELIMITER);
            console.log("date from parse= "+JSON.stringify(date));
            return {
                day : parseInt(date[2], 10),
                month: parseInt(date[1], 10),
                year: parseInt(date[0], 10)
            };
        }
        return null;
    }

    /** yyyY-MM-dd */
    toModel(date: NgbDateStruct | null):string | null {
        return date ? date.year + this.DELIMITER + ('0'+date.month).slice(-2) + this.DELIMITER + ('0'+date.day).slice(-2) : null;
    }

    studentCreateForm = new FormGroup({
        student_id: new FormControl(''),
        student_name: new FormControl('', [Validators.required, Validators.minLength(5)]),
        student_email: new FormControl('', [Validators.required, Validators.minLength(5)]),
        student_branch: new FormControl(''),
        student_phone_number: new FormControl('', [Validators.required, Validators.minLength(13)]),
        student_date_of_birth: new FormControl('', [Validators.required])
    });

    createStudent(){
        this.student = new Student();
        this.student.student_name = this.studentCreateForm.get('student_name')?.value!;
        this.student.student_email = this.studentCreateForm.get('student_email')?.value!;
        this.student.student_phone_number = this.studentCreateForm.get('student_phone_number')?.value!;
        this.student.student_date_of_birth = this.toModel(this.model)!;
        this.student.student_branch = this.studentCreateForm.get('student_branch')?.value!;
        
        console.log("create student="+JSON.stringify(this.student));
        console.log("execute service..")
        this.studentService.doCallCreateStudent(this.student)
            .subscribe(data => {
                console.log(JSON.stringify(data));
                this.student = new Student();
                this.studentCreateForm.reset();
                console.log("success create data")
            },err => {
                console.log("error because="+JSON.stringify(err.error));
                alert(JSON.stringify(err.error.message));
            });
    }

    updateStudent(){
        this.student = new Student();
        var studentId = this.studentCreateForm.get('student_id')?.value!;
        this.student.student_id = studentId;
        this.student.student_branch=this.studentCreateForm.get('student_branch')?.value!;
        this.student.student_email=this.studentCreateForm.get('student_email')?.value!;
        this.student.student_phone_number=this.studentCreateForm.get('student_phone_number')?.value!;
        this.student.student_name=this.studentCreateForm.get('student_name')?.value!;
        this.student.student_date_of_birth=this.toModel(this.model)!;

        console.log("update student="+JSON.stringify(this.student));
        console.log("execute service..");
        this.studentService.doCallUpdateStudent(this.student)
            .subscribe(response => {
                console.log(JSON.stringify(response));
                this.student = new Student();
                this.studentCreateForm.reset();
            },err=>{
                console.log(err.error);
                alert(JSON.stringify(err.error.message));
            });
        
    }

    detailIdStudent(id:number){
        this.selectedIdStudent = id;
        console.log("from create component detail id student -> "+id);
        this.studentService.doCallDetailStudentById(this.selectedIdStudent)
            .subscribe(response => {
                var dataParse = JSON.parse(JSON.stringify(response.data));
                console.log("response detail student->"+JSON.stringify(response.data));
                this.studentCreateForm.get('student_id')?.setValue(dataParse.student_id);
                this.studentCreateForm.get('student_name')?.setValue(dataParse.student_name);
                this.studentCreateForm.get('student_email')?.setValue(dataParse.student_email);
                this.studentCreateForm.get('student_phone_number')?.setValue(dataParse.student_phone_number);
                this.studentCreateForm.get('student_branch')?.setValue(dataParse.student_branch);
                this.studentCreateForm.get('student_date_of_birth')?.setValue(dataParse.student_date_of_birth);
                this.model = this.parse(dataParse.student_date_of_birth)!;
                console.log("model data after parse -> "+JSON.stringify(this.model));
                this.currentBirthDateSelected = dataParse.student_date_of_birth;
                console.log("current birthday selected -> "+this.currentBirthDateSelected);
            },err => console.log(err.error));
    }

}