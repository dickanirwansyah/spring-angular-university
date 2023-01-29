import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-list-faculty',
    templateUrl: './list-faculty.component.html'
})
export class ListFacultyComponent implements OnInit{

    ngOnInit(): void {
        console.log("this is list faculty!")
    }

}