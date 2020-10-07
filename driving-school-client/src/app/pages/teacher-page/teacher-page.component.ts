import {Component, OnInit, ViewChild} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {TeacherService} from '../../shared/services/teacher.service';
import {TeacherEditModalComponent} from './components/teacher-edit-modal/teacher-edit-modal.component';
import {TeacherAddModalComponent} from './components/teacher-add-modal/teacher-add-modal.component';
import {TeacherDeleteModalComponent} from './components/teacher-delete-modal/teacher-delete-modal.component';

@Component({
  selector: 'app-teacher-list',
  templateUrl: './teacher-page.component.html',
  styleUrls: ['./teacher-page.component.css']
})
export class TeacherPageComponent implements OnInit {
  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'createDate', 'updateDate', 'action'];
  public teachers;
  isLoading = true;
  placeName = `Teacher List`;
  @ViewChild(MatPaginator, {static: true})
  paginator: MatPaginator;
  @ViewChild(MatSort, {static: true})
  sort: MatSort;

  constructor(private teacherService: TeacherService,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getAllTeacher();
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.teachers.filter = filterValue.trim().toLowerCase();

    if (this.teachers.paginator) {
      this.teachers.paginator.firstPage();
    }
  }

  getAllTeacher(): void {
    this.teacherService.getAllTeacher().subscribe(
      data => this.teachers = new MatTableDataSource(data),
      err => console.error(err),
      () => {
        console.log('teachers loaded');
        this.teachers.paginator = this.paginator;
        this.paginator.pageSize = 20;
        this.teachers.sort = this.sort;
        this.isLoading = false;

      }
    );
  }

  openEditTeacherModal(teacherId: number, userId: number): void {
    const modalRef = this.modalService.open(TeacherEditModalComponent);
    modalRef.componentInstance.teacherId = teacherId;
    modalRef.componentInstance.userId = userId;
  }

  openDeleteTeacherModal(teacherId: number, userId: number): void {
    const modalRef = this.modalService.open(TeacherDeleteModalComponent);
    modalRef.componentInstance.teacherId = teacherId;
    modalRef.componentInstance.userId = userId;
  }

  openAddTeacherModal(): void {
    const modalRef = this.modalService.open(TeacherAddModalComponent);
  }

  refreshList(): void {
    this.getAllTeacher();
  }
}
