import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {StudentEditModalComponent} from './components/student-edit-modal/student-edit-modal.component';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {StudentAddModalComponent} from './components/student-add-modal/student-add-modal.component';
import {IStudent} from '../../shared/model/Student';
import {StudentDeleteModalComponent} from './components/student-delete-modal/student-delete-modal.component';
import {Select, Store} from '@ngxs/store';
import {combineLatest, merge, Observable, of, Subject} from 'rxjs';
import {StudentsListState} from '../../shared/state/students-list/students-list.state';
import {debounceTime, map} from 'rxjs/operators';
import {FetchStudents} from '../../shared/state/students-list/students-list.actions';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-page.component.html',
  styleUrls: ['./student-page.component.css']
})


export class StudentPageComponent implements AfterViewInit, OnInit{
  placeName = `Student List`;
  displayedOnlyDataColumns: string[] = ['id', 'firstName', 'lastName', 'address', 'hours', 'status', 'category', 'createDate'];
  displayedColumns: string[] = [...this.displayedOnlyDataColumns, 'action'];
  @ViewChild(MatPaginator)  paginator: MatPaginator;
  @ViewChild(MatSort)  sort: MatSort;
  isLoading = true;
  tableStudentsLength = 0;
  tableStudentsNoFilter$: Observable<MatTableDataSource<IStudent>>;
  tableStudents$: Observable<MatTableDataSource<IStudent>>;
  private searchSubject = new Subject<string>();
  searchAction$: Observable<string>;
  filterAction$: Observable<string>;

  @Select(StudentsListState.getAllStudents)
  students$: Observable<IStudent[]>;

  constructor(private modalService: NgbModal,
              private store: Store) {
    this.tableStudentsNoFilter$ = merge(this.students$)
     .pipe(
       map( student => {
         const tableStudents = new MatTableDataSource(student as IStudent[]);
         tableStudents.paginator = this.paginator;
         tableStudents.sort = this.sort;
         this.isLoading = false;
         this.tableStudentsLength = tableStudents.data.length;
         return tableStudents;
       })
     );

    this.filterAction$ = merge(
      this.searchSubject.asObservable().pipe(debounceTime(1000)),
      of('')
    );

    this.tableStudents$ = combineLatest([this.tableStudentsNoFilter$, this.filterAction$])
     .pipe(
       map(([tableStudents, search]) => {
         tableStudents.filter = search.trim().toLowerCase();
         if (tableStudents.paginator) {
           tableStudents.paginator.firstPage();
         }
         return tableStudents;
       })
     );
  }

  ngAfterViewInit(): void {
    this.getAllStudent();
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
  }

  ngOnInit(): void {
  }

  getAllStudent(): void {
    this.store.dispatch(new FetchStudents());
  }

  openEditStudentModal(studentId: number, userId: number): void {
    const modalRef = this.modalService.open(StudentEditModalComponent);
    modalRef.componentInstance.studentId = studentId;
    modalRef.componentInstance.userId = userId;
  }

  openAddStudentModal(): void {
    this.modalService.open(StudentAddModalComponent);
  }

  openDeleteStudentModal(studentId: number, userId: number): void  {
    const modalRef = this.modalService.open(StudentDeleteModalComponent);
    modalRef.componentInstance.studentId = studentId;
    modalRef.componentInstance.userId = userId;
  }

  refreshList(): void {
    this.isLoading = true;
    this.getAllStudent();
  }

  onInputTextFilter(text: string): void {
    this.searchSubject.next(text);
  }

  openViewTeacherModal(studentId: number, userId: number): void {
    const modalRef = this.modalService.open(StudentEditModalComponent);
    modalRef.componentInstance.studentId = studentId;
    modalRef.componentInstance.userId = userId;
  }
}
