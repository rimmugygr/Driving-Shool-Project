import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {TeacherService} from '../../../../services/teacher.service';
import {Teacher} from '../../../../model/Teacher';
import {User} from '../../../../model/User';
import {Student} from '../../../../model/Student';

@Component({
  selector: 'app-teacher-edit-modal',
  templateUrl: './teacher-edit-modal.component.html',
  styleUrls: ['./teacher-edit-modal.component.css']
})
export class TeacherEditModalComponent implements OnInit {
  @Input() teacherId;
  @Input() userId;
  teacher: Teacher = new Teacher();
  teacherEditedSaved = false;
  hide = true;
  postForm: FormGroup;
  message = '';



  constructor(public modal: NgbActiveModal,
              private teacherService: TeacherService) { }

  ngOnInit(): void {
    this.getTeacherThenInitForm();
  }

  submitPost(): void {
    let newTeacher: Teacher = new Teacher();
    let result = '';
    if (this.postForm.valid) {
      this.message = 'send data to server';
      newTeacher = this.postForm.value as Teacher;
      newTeacher.id = this.teacherId;
      newTeacher.user = new User( this.userId, this.postForm.value.username, this.postForm.value.password);
      this.teacherService.patchTeacher(newTeacher, this.teacherId).subscribe(
        data => {
          this.teacherEditedSaved = true;
          result = data;
        },
        error => this.message = 'Error ' + result,
        () => this.message = 'Edited ' + newTeacher.firstName + ' ' + newTeacher.lastName + ' on id ' + newTeacher.id
      );
    } else {
      this.message = 'Pleas fill form !';
    }
  }

  private getTeacherThenInitForm(): void {
    this.teacherService.getTeacher(this.teacherId).subscribe(
      () => { this.teacherEditedSaved = true; },
      error => { this.message = 'Error ' + JSON.stringify(error.error); },
      () => {
        console.log('success load teacher');
        this.initForm();
      }
    );
  }

  private initForm(): void {
    this.postForm = new FormGroup({
      firstName: new FormControl(this.teacher.firstName, Validators.required),
      lastName: new FormControl(this.teacher.lastName, Validators.required),
      username: new FormControl(this.teacher.user.username, Validators.required),
      password: new FormControl(this.teacher.user.password, Validators.required)
    });
  }
}
