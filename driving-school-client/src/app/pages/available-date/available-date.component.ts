import {Component, OnInit, ViewChild} from '@angular/core';
import {AvailableDate} from '../../model/AvailableDate';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AvailableService} from '../../services/available.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-available-date',
  templateUrl: './available-date.component.html',
  styleUrls: ['./available-date.component.css']
})
export class AvailableDateComponent implements OnInit {
  availableDate: MatTableDataSource<AvailableDate>;
  displayedColumns: string[];
  isLoading = true;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor( private modalService: NgbModal,
               private availableService: AvailableService) { }

  ngOnInit(): void {
    this.getAvailableDate();
  }

  refreshList(): void {

  }

  openAddStudentModal(): void {

  }

  openDeleteStudentModal(id: any, id2: ArrayBufferView | ArrayBuffer): void {

  }

  getAvailableDate(): void {
    this.availableService.getAllAvailableDate().subscribe(
      data => this.availableDate = new MatTableDataSource(data as AvailableDate[]),
      error => console.log(error),
      () => {
       console.log('available date loaded');
       this.availableDate.paginator = this.paginator;
       this.paginator.pageSize = 20;
       this.availableDate.sort = this.sort;
       this.isLoading = false;
      });
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.availableDate.filter = filterValue.trim().toLowerCase();

    if (this.availableDate.paginator) {
      this.availableDate.paginator.firstPage();
    }
  }
}
