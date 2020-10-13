import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {IStudent} from '../../shared/model/Student';
import {Select, Store} from '@ngxs/store';
import {combineLatest, merge, Observable, of, Subject} from 'rxjs';
import {ProfilesStudentsListState} from '../../shared/state/profiles-students-list/profiles-students-list-state.service';
import {debounceTime, map} from 'rxjs/operators';
import {FetchStudents} from '../../shared/state/profiles-students-list/profiles-students-list.actions';
import {ProfilesStudentsFormModalComponent} from './components/profiles-students-form-modal/profiles-students-form-modal.component';
import {FORM_PAGE_MODE} from '../../shared/model/FormPageMode';

@Component({
  selector: 'app-student-list',
  templateUrl: './profiles-students-page.component.html',
  styleUrls: ['./profiles-students-page.component.css']
})


export class ProfilesStudentsPageComponent implements AfterViewInit, OnInit{
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

  @Select(ProfilesStudentsListState.getAllStudents)
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
    const modalRef = this.modalService.open(ProfilesStudentsFormModalComponent);
    modalRef.componentInstance.pageMode = FORM_PAGE_MODE.EDIT;
    modalRef.componentInstance.studentId = studentId;
    modalRef.componentInstance.userId = userId;
  }

  openAddStudentModal(): void {
    const modalRef = this.modalService.open(ProfilesStudentsFormModalComponent);
    modalRef.componentInstance.pageMode = FORM_PAGE_MODE.CREATE;
  }

  openDeleteStudentModal(studentId: number, userId: number): void  {
    const modalRef = this.modalService.open(ProfilesStudentsFormModalComponent);
    modalRef.componentInstance.pageMode = FORM_PAGE_MODE.DELETE;
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
    const modalRef = this.modalService.open(ProfilesStudentsFormModalComponent);
    modalRef.componentInstance.pageMode = FORM_PAGE_MODE.DETAILS;
    modalRef.componentInstance.studentId = studentId;
    modalRef.componentInstance.userId = userId;
  }
}
