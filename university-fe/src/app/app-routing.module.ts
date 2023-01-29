import { CreateFacultyComponent } from './create-faculty/create-faculty.component';
import { CreateStudentComponent } from './create-student/create-student.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: '', redirectTo: 'list-student', pathMatch: 'full'},
  {path: 'create-faculty', component: CreateFacultyComponent},
  {path: 'create-student', component: CreateStudentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
