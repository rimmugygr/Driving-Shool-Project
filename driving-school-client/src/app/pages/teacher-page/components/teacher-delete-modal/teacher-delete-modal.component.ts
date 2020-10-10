import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {ITeacher} from '../../../../shared/model/Teacher';
import {TeacherService} from '../../../../shared/services/teacher.service';

@Component({
  selector: 'app-teacher-delete-modal',
  templateUrl: './teacher-delete-modal.component.html',
  styleUrls: ['./teacher-delete-modal.component.css']
})
export class TeacherDeleteModalComponent implements OnInit {
  @Input() teacherId;
  teacher: ITeacher;
  message: string;
  deleted = false;

  constructor(public modal: NgbActiveModal,
              private teacherService: TeacherService) { }

  ngOnInit(): void {
    this.getStudent();
  }

  deleteTeacher(): void {
    this.teacherService.deleteTeacher(this.teacherId).subscribe(
      () => { } ,
      error => { this.message = 'Error ' + JSON.stringify(error.error); },
      () => {
        this.message = 'Deleted student on id ' + this.teacherId;
        this.deleted = true;
      }
    );
  }

  private getStudent(): void {
    this.teacherService.getTeacherResponse(this.teacherId).subscribe(
      data => this.teacher = data as ITeacher,
      error => console.error(error),
      () => console.log('success load teacher')
    );
  }
}
