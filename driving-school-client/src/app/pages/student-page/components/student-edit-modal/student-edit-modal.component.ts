import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Student} from '../../../../shared/model/Student';
import {StudentService} from '../../../../shared/services/student.service';
import {User} from '../../../../shared/model/User';

@Component({
  selector: 'app-student-edit-modal',
  templateUrl: './student-edit-modal.component.html',
  styleUrls: ['./student-edit-modal.component.css']
})
export class StudentEditModalComponent implements OnInit {
  @Input() studentId;
  @Input() userId;
  student: Student = new Student();
  hide = true;
  message = '';
  studentEditedSaved = false;
  postForm: FormGroup;



  constructor(public modal: NgbActiveModal,
              private studentService: StudentService) { }


  ngOnInit(): void {
    this.getStudentThenInitForm();
  }

  submitPost(): void {
    let newStudent: Student;
    if (this.postForm.valid) {
      this.message = 'send data to server';
      newStudent = this.postForm.value;
      newStudent.createDate =  this.student.createDate;
      newStudent.hours = this.student.hours;
      newStudent.id = this.studentId;
      newStudent.user = new User( this.userId, this.postForm.value.username, this.postForm.value.password);
      this.studentService.patchStudent(newStudent, this.studentId).subscribe(
        () => { this.studentEditedSaved = true; },
          error => { this.message = 'Error ' + JSON.stringify(error.error); },
        () => this.message = 'Edited ' + newStudent.firstName + ' ' + newStudent.lastName + ' on id ' + newStudent.id
      );
    } else {
      this.message = 'Pleas fill form correct!';
    }
  }


  private getStudentThenInitForm(): void {
    this.studentService.getStudent(this.studentId).subscribe(
      data => this.student = data as Student,
      error => console.error(error),
      () => {
        console.log('success load student');
        this.initForm();
      }
    );
  }

  private initForm(): void {
    this.postForm = new FormGroup({
      email: new FormControl(this.student.email, [Validators.email, Validators.required]),
      firstName: new FormControl(this.student.firstName, Validators.required),
      lastName: new FormControl(this.student.lastName, Validators.required),
      address: new FormControl(this.student.address, Validators.required),
      username: new FormControl(this.student.user.username, Validators.required),
      password: new FormControl(this.student.user.password, Validators.required),
      phone: new FormControl(this.student.phone, Validators.required),
      status: new FormControl(this.student.status, Validators.required),
      createDate: new FormControl({value: this.student.createDate, disabled: true}, Validators.required),
      hours: new FormControl({value: this.student.hours, disabled: true}, Validators.required)
    });
  }
}
