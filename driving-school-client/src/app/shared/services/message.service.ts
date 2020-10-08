import { Injectable } from '@angular/core';
import {ToastrService} from 'ngx-toastr';

const TIMESPAN = 5000;

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private toastr: ToastrService) { }

  showSuccess(message, title): void{
    this.toastr.success(message, title, { timeOut :  TIMESPAN });
  }

  showErrors(message, title): void{
    this.toastr.error(message, title, { timeOut :  TIMESPAN });
  }
}
