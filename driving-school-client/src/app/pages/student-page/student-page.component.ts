import {Component, OnInit, Type, ViewChild} from '@angular/core';
import {StudentService} from '../../shared/services/student.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {StudentEditModalComponent} from './components/student-edit-modal/student-edit-modal.component';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {StudentAddModalComponent} from './components/student-add-modal/student-add-modal.component';
import {Student} from '../../shared/model/Student';
import {StudentDeleteModalComponent} from './components/student-delete-modal/student-delete-modal.component';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-page.component.html',
  styleUrls: ['./student-page.component.css']
})



export class StudentPageComponent implements OnInit {
  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'address', 'hours', 'status', 'category', 'createDate', 'action'];
  public students;
  @ViewChild(MatPaginator, {static: true})
  paginator: MatPaginator;
  @ViewChild(MatSort, {static: true})
  sort: MatSort;
  isLoading = true;
  placeName = `Student List`;

  constructor(private studentService: StudentService,
              private modalService: NgbModal) { }


  ngOnInit(): void {
    this.getAllStudent();
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.students.filter = filterValue.trim().toLowerCase();

    if (this.students.paginator) {
      this.students.paginator.firstPage();
    }
  }

  getAllStudent(): void {
    this.studentService.getAllStudent().subscribe(
      data => this.students = new MatTableDataSource(data as Student[]),
      err => console.error(err),
      () => {
        console.log('student loaded');
        this.students.paginator = this.paginator;
        this.paginator.pageSize = 20;
        this.students.sort = this.sort;
        this.isLoading = false;

      }
    );
  }

  openEditStudentModal(studentId: number, userId: number): void {
    const modalRef = this.modalService.open(StudentEditModalComponent);
    modalRef.componentInstance.studentId = studentId;
    modalRef.componentInstance.userId = userId;
  }

  openAddStudentModal(): void {
    const modalRef = this.modalService.open(StudentAddModalComponent);
  }

  openDeleteStudentModal(studentId: number, userId: number): void  {
    const modalRef = this.modalService.open(StudentDeleteModalComponent);
    modalRef.componentInstance.studentId = studentId;
    modalRef.componentInstance.userId = userId;
  }

  refreshList(): void {
    this.getAllStudent();
  }
}