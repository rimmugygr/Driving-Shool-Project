import {Component, OnInit, ViewChild} from '@angular/core';
import {AvailableDate} from '../../shared/model/AvailableDate';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AvailableService} from '../../shared/services/available.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-available-date',
  templateUrl: './available-date-page.component.html',
  styleUrls: ['./available-date-page.component.css']
})
export class AvailableDatePageComponent implements OnInit {
  availableDate: MatTableDataSource<AvailableDate>;
  displayedColumns: string[];
  isLoading = true;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  placeName = `Available Date List`;

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
