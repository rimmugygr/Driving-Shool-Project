import {Component, EventEmitter, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {StudentService} from '../../../../shared/services/student.service';
import { Student, } from '../../../../shared/model/Student';
import {User} from '../../../../shared/model/User';

@Component({
  selector: 'app-student-add-modal',
  templateUrl: './student-add-modal.component.html',
  styleUrls: ['./student-add-modal.component.css']
})
export class StudentAddModalComponent implements OnInit {
  hide = true;
  postForm: FormGroup;
  message = '';


  constructor(public modal: NgbActiveModal,
              private studentService: StudentService) { }

  ngOnInit(): void {
    this.initForm();
  }

  submitPost(): void {
    let newStudent: Student = new Student();
    if (this.postForm.valid) {
      this.message = 'send data to server';
      newStudent = this.postForm.value as Student;
      newStudent.hours = 0;
      newStudent.user = new User( null, this.postForm.value.username, this.postForm.value.password);
      this.studentService.addStudent(newStudent).subscribe(
        () => { } ,
        error => { this.message = 'Error ' + JSON.stringify(error.error); },
        () => this.message = 'Added ' + newStudent.firstName + ' ' + newStudent.lastName
      );
    } else {
      this.message = 'Pleas fill form !';
    }
  }

  private initForm(): void {
    this.postForm = new FormGroup({
      email: new FormControl('', [Validators.email, Validators.required]),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      address: new FormControl('', Validators.required),
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      phone: new FormControl('', Validators.required),
      status: new FormControl('Active', Validators.required),
      hours: new FormControl(0, Validators.required)
    });
  }


}
