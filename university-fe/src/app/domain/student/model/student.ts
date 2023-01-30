import { Faculty } from './../../faculty/model/faculty';

export class Student {
    student_id: any;
    student_name: string;
    student_email: string;
    student_branch: string;
    student_phone_number: string;
    student_date_of_birth: string;
    student_faculty: Faculty;
    student_faculty_id: number;
    student_current_semester: number;
}