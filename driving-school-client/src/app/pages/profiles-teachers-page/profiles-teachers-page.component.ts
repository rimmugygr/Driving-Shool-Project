import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {ProfilesTeachersFormModalComponent} from './components/profiles-teachers-form-modal/profiles-teachers-form-modal.component';
import {FORM_PAGE_MODE} from '../../shared/model/FormPageMode';
import {combineLatest, merge, Observable, of, Subject} from 'rxjs';
import {Select, Store} from '@ngxs/store';
import {ProfilesTeachersListState} from '../../shared/state/profiles-teachers-list/profiles-teachers-list-state.service';
import {debounceTime, map} from 'rxjs/operators';
import {FetchTeachers} from '../../shared/state/profiles-teachers-list/profiles-teachers-list.actions';
import {ITeacher} from '../../shared/model/Teacher';

@Component({
  selector: 'app-teacher-list',
  templateUrl: './profiles-teachers-page.component.html',
  styleUrls: ['./profiles-teachers-page.component.css']
})
export class ProfilesTeachersPageComponent implements AfterViewInit, OnInit {
  placeName = `Teacher List`;
  displayedOnlyDataColumns: string[] =  ['id', 'firstName', 'lastName', 'createDate', 'updateDate'];
  displayedColumns: string[] = [...this.displayedOnlyDataColumns, 'action'];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  isLoading = true;
  tableTeachersLength = 0;
  tableTeachersNoFilter$: Observable<MatTableDataSource<ITeacher>>;
  tableTeachers$: Observable<MatTableDataSource<ITeacher>>;
  private searchSubject = new Subject<string>();
  searchAction$: Observable<string>;
  filterAction$: Observable<string>;

  @Select(ProfilesTeachersListState.getAllTeachers)
  teachers$: Observable<ITeacher[]>;

  constructor(private modalService: NgbModal,
              private store: Store) {
    this.tableTeachersNoFilter$ = merge(this.teachers$)
      .pipe(
        map( teachers => {
          const tableTeachers = new MatTableDataSource(teachers as ITeacher[]);
          tableTeachers.paginator = this.paginator;
          tableTeachers.sort = this.sort;
          this.isLoading = false;
          this.tableTeachersLength = tableTeachers.data.length;
          return tableTeachers;
        })
      );

    this.filterAction$ = merge(
      this.searchSubject.asObservable().pipe(debounceTime(1000)),
      of('')
    );

    this.tableTeachers$ = combineLatest([this.tableTeachersNoFilter$, this.filterAction$])
      .pipe(
        map(([tableTeachers, search]) => {
          tableTeachers.filter = search.trim().toLowerCase();
          if (tableTeachers.paginator) {
            tableTeachers.paginator.firstPage();
          }
          return tableTeachers;
        })
      );
  }

  ngAfterViewInit(): void {
    this.getAllTeacher();
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
  }

  ngOnInit(): void {
  }

  getAllTeacher(): void {
    this.store.dispatch(new FetchTeachers());
  }

  onInputTextFilter(text: string): void {
    this.searchSubject.next(text);
  }

  openEditTeacherModal(teacherId: number, userId: number): void {
    const modalRef = this.modalService.open(ProfilesTeachersFormModalComponent);
    modalRef.componentInstance.pageMode = FORM_PAGE_MODE.EDIT;
    modalRef.componentInstance.teacherId = teacherId;
    modalRef.componentInstance.userId = userId;
  }

  openViewTeacherModal(teacherId: number, userId: number): void {
    const modalRef = this.modalService.open(ProfilesTeachersFormModalComponent);
    modalRef.componentInstance.pageMode = FORM_PAGE_MODE.DETAILS;
    modalRef.componentInstance.teacherId = teacherId;
    modalRef.componentInstance.userId = userId;
  }

  openDeleteTeacherModal(teacherId: number, userId: number): void {
    const modalRef = this.modalService.open(ProfilesTeachersFormModalComponent);
    modalRef.componentInstance.pageMode = FORM_PAGE_MODE.DELETE;
    modalRef.componentInstance.teacherId = teacherId;
    modalRef.componentInstance.userId = userId;
  }

  openAddTeacherModal(): void {
    const modalRef = this.modalService.open(ProfilesTeachersFormModalComponent);
    modalRef.componentInstance.pageMode = FORM_PAGE_MODE.CREATE;
  }

  refreshList(): void {
    this.isLoading = true;
    this.getAllTeacher();
  }
}
