import { Faculty } from './../domain/faculty/model/faculty';
import { FacultyService } from './../domain/faculty/service/faculty.service';
import { Student } from './../domain/student/model/student';
import { StudentService } from './../domain/student/service/student.service';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
    selector: 'app-create-student',
    templateUrl: './create-student.component.html'
})
export class CreateStudentComponent implements OnInit{

    student:Student=new Student();
    subitted=false;
    model: NgbDateStruct;
    parseDate:string|null;
    titlePage = 'Create Student';
    readonly DELIMITER = "-";
    selectedIdStudent: number;
    isEdit:boolean;
    currentBirthDateSelected:string|null;
    listFaculty: Faculty[];
    form!: FormGroup;
    result: any;    
    facultySelected:any;
    selectedData: any;

    /** using ng model form */
    createStudent: Student = new Student();
    editStudent: Student = new Student();

    constructor(private studentService:StudentService,
        private facultyService:FacultyService,
        private formBuilder:FormBuilder){}

    ngOnInit(): void {
        console.log("this is page create student..")
        this.subitted=false;
        console.log("detail id selected student -> "+this.selectedIdStudent);
        this.isEdit=false;
        this.selectedIdStudent=0;
        this.initialDropdownFaculty();
        this.studentCreateForm();
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

    studentCreateForm(){
        this.form = this.formBuilder.group({
            student_id: new FormControl(''),
            student_name: new FormControl('', [Validators.required, Validators.minLength(5)]),
            student_branch: new FormControl(''),
            student_email: new FormControl('',[Validators.required]),
            student_phone_number: new FormControl('', [Validators.required, Validators.minLength(13)]),
            student_date_of_birth: new FormControl('', [Validators.required]),
            student_faculty: this.studentFacultyForm(),
            student_faculty_id: new FormControl(''),
            student_current_semester: new FormControl('')
        })
    }

    studentFacultyForm(){
        return this.formBuilder.group({
            faculty_id: new FormControl('', [Validators.required]),
            faculty_name: new FormControl('', [Validators.required]),
            faculty_activated: new FormControl('', [Validators.required])
        });
    }


    detailIdStudent(id:number){
        this.selectedIdStudent = id;
        console.log("from create component detail id student -> "+id);
        this.studentService.doCallDetailStudentById(this.selectedIdStudent)
            .subscribe(response => {
                var dataParse = JSON.parse(JSON.stringify(response.data));
                console.log("response detail student->"+JSON.stringify(response.data));
                

                this.form.get('student_id')?.setValue(dataParse.student_id);
                this.form.get('student_name')?.setValue(dataParse.student_name);
                this.form.get('student_email')?.setValue(dataParse.student_email);
                this.form.get('student_phone_number')?.setValue(dataParse.student_phone_number);
                this.form.get('student_branch')?.setValue(dataParse.student_branch);
                this.form.get('student_date_of_birth')?.setValue(dataParse.student_date_of_birth);
                this.model = this.parse(dataParse.student_date_of_birth)!;
                this.form.get('student_current_semester')?.setValue(dataParse.student_current_semester);
                console.log("model data after parse -> "+JSON.stringify(this.model));
                this.currentBirthDateSelected = dataParse.student_date_of_birth;
                this.facultySelected = dataParse.student_faculty;
                console.log("faculty convert ->"+JSON.stringify(this.facultySelected));
                
                for (let i=0; i < this.listFaculty.length; i++){
                    if (this.facultySelected.faculty_id == this.listFaculty[i].faculty_id){
                        this.listFaculty[i];
                        console.log("faculty filter -> "+JSON.stringify(this.listFaculty[i]));
                        this.form.get('student_faculty_id')?.setValue(this.listFaculty[i].faculty_id)
                        this.form.get('student_faculty')?.setValue(this.listFaculty[i])
                    }
                }

            },err => console.log(err.error));
    }


    initialDropdownFaculty(){
        this.facultyService.doCallListFaculty().subscribe(data => {
            this.listFaculty = data.data.listFaculty;
            console.log("response faculty->"+JSON.stringify(this.listFaculty));
        },error => console.log(error));
    }

    onSave(){
        console.log("execute save..");
    
        var dataSelected = JSON.parse(JSON.stringify(this.form.getRawValue()));
        console.log("selected faculty create -> ",dataSelected['student_faculty_id']);

        for (let i=0; i < this.listFaculty.length; i++){
            if (dataSelected['student_faculty_id'] == this.listFaculty[i].faculty_id){
                this.listFaculty[i];
                console.log("faculty filter -> "+JSON.stringify(this.listFaculty[i]));
                this.form.get('student_faculty_id')?.setValue(this.listFaculty[i].faculty_id)
                this.form.get('student_faculty')?.setValue(this.listFaculty[i])
            }
        }
        this.form.get('student_id')?.setValue(0);
        this.form.get('student_date_of_birth')?.setValue(this.toModel(this.model));
        console.log("request data save -> "+JSON.stringify(this.form.getRawValue()));

        /** excute save */
        this.studentService.doCallCreateStudent(this.form.getRawValue())
            .subscribe(response => {
                console.log("response save = "+JSON.stringify(response));
                this.form.reset();
                window.location.reload();
            },err => err.error);
    }

    onUpdate(){
        console.log("execute update..");
        console.log("data -> "+JSON.stringify(this.form.getRawValue()));
        /** update data */

        var dataSelected = JSON.parse(JSON.stringify(this.form.getRawValue()));
        console.log("selected faculty create -> ",dataSelected['student_faculty_id']);

        for (let i=0; i < this.listFaculty.length; i++){
            if (dataSelected['student_faculty_id'] == this.listFaculty[i].faculty_id){
                this.listFaculty[i];
                console.log("faculty filter -> "+JSON.stringify(this.listFaculty[i]));
                this.form.get('student_faculty_id')?.setValue(this.listFaculty[i].faculty_id)
                this.form.get('student_faculty')?.setValue(this.listFaculty[i])
            }
        }
        this.form.get('student_date_of_birth')?.setValue(this.toModel(this.model));
        console.log("request data update -> "+JSON.stringify(this.form.getRawValue()));

        /** excute update */
        this.studentService.doCallUpdateStudent(this.form.getRawValue())
            .subscribe(response => {
                console.log("response update -> "+JSON.stringify(response));
                this.form.reset();
                window.location.reload();
            }, err => err.error);
    }

}