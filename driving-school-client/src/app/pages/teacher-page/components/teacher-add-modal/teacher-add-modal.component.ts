import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {User} from '../../../../shared/model/User';
import {TeacherService} from '../../../../shared/services/teacher.service';
import {Teacher} from '../../../../shared/model/Teacher';

@Component({
  selector: 'app-teacher-add-modal',
  templateUrl: './teacher-add-modal.component.html',
  styleUrls: ['./teacher-add-modal.component.css']
})
export class TeacherAddModalComponent implements OnInit {
  hide = true;
  postForm: FormGroup;
  message = '';



  constructor(public modal: NgbActiveModal,
              private teacherService: TeacherService) { }

  ngOnInit(): void {
    this.initForm();
  }

  submitPost(): void {
    let newTeacher: Teacher = new Teacher();
    if (this.postForm.valid) {
      this.message = 'send data to server';
      newTeacher = this.postForm.value as Teacher;
      newTeacher.user = new User( null, this.postForm.value.username, this.postForm.value.password);
      this.teacherService.postTeacher(newTeacher).subscribe(
        () => { } ,
        error => { this.message = 'Error ' + JSON.stringify(error.error); },
        () => this.message = 'Added ' + newTeacher.firstName + ' ' + newTeacher.lastName
      );
    } else {
      this.message = 'Pleas fill form !';
    }
  }

  private initForm(): void {
    this.postForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }
}
