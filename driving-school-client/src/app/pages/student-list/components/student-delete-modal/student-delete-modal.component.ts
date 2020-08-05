import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {StudentService} from '../../../../services/student.service';
import {Student} from '../../../../model/Student';

@Component({
  selector: 'app-student-delete-modal',
  templateUrl: './student-delete-modal.component.html',
  styleUrls: ['./student-delete-modal.component.css']
})
export class StudentDeleteModalComponent implements OnInit {
  @Input() studentId;
  student: Student = new Student();
  message: string;
  deleted = false;

  constructor(public modal: NgbActiveModal,
              private studentService: StudentService) { }

  ngOnInit(): void {
    this.getStudent();
  }

  deleteStudent(): void {
    this.studentService.deleteStudent(this.studentId).subscribe(
      () => { } ,
      error => { this.message = 'Error ' + JSON.stringify(error.error); },
      () => {
        this.message = 'Deleted student on id ' + this.studentId;
        this.deleted = true;
      }
    );
  }

  private getStudent(): void {
    this.studentService.getStudent(this.studentId).subscribe(
      data => this.student = data as Student,
      error => console.error(error),
      () => console.log('success load student')
    );
  }



}
