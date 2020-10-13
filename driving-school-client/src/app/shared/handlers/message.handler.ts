import {APP_INITIALIZER, Injectable, Injector} from '@angular/core';
import {Actions, ofActionErrored, ofActionSuccessful, Store} from '@ngxs/store';
import {Login, Logout} from '../state/user-auth/user-auth.actions';
import {MessageService} from '../services/message.service';
import {CreateStudent, DeleteStudent, UpdateStudent} from '../state/profiles-students-list/profiles-students-list.actions';
import {CreateTeacher, DeleteTeacher, UpdateTeacher} from '../state/profiles-teachers-list/profiles-teachers-list.actions';

@Injectable({
  providedIn: 'root'
})
export class MessageHandler {

  constructor(
    private actions$: Actions,
    private store: Store,
    private injector: Injector
  ) {

    this.actions$
      .pipe(ofActionSuccessful(Login))
      .subscribe(result => {
        const message = this.injector.get(MessageService);
        message.showSuccess('Successful login as ' + result.payload.loginRequest.username, 'Login');
      });

    this.actions$
      .pipe(ofActionErrored(Login))
      .subscribe(result => {
        const message = this.injector.get(MessageService);
        message.showErrors('Error on login as' + result.payload.loginRequest.username , 'Login');
      });

    this.actions$
      .pipe(ofActionSuccessful(Logout))
      .subscribe(result => {
        const message = this.injector.get(MessageService);
        message.showSuccess('Successful logout ', 'Logout');
      });

    this.actions$
      .pipe(ofActionErrored(Logout))
      .subscribe(result => {
        const message = this.injector.get(MessageService);
        message.showErrors('Error on logout', 'Logout');
      });

    this.actions$
      .pipe(ofActionSuccessful(CreateStudent, UpdateStudent, DeleteStudent))
      .subscribe(result => {
        const message = this.injector.get(MessageService);
        message.showSuccess('Successful action on student', 'Student');
      });

    this.actions$
      .pipe(ofActionErrored(CreateStudent, UpdateStudent, DeleteStudent))
      .subscribe(result => {
        const message = this.injector.get(MessageService);
        message.showErrors('Error in action on student', 'Student');
      });

    this.actions$
      .pipe(ofActionSuccessful(CreateTeacher, UpdateTeacher, DeleteTeacher))
      .subscribe(result => {
        const message = this.injector.get(MessageService);
        message.showSuccess('Successful action on teacher', 'Teacher');
      });

    this.actions$
      .pipe(ofActionErrored(CreateTeacher, UpdateTeacher, DeleteTeacher))
      .subscribe(result => {
        const message = this.injector.get(MessageService);
        message.showErrors('Error in action on teacher', 'Teacher');
      });
  }
}


const initFn = () => () => { /* use for some initialization stuff */ };

export const messageHandlerProviders = {
  provide: APP_INITIALIZER,
  useFactory: initFn,
  deps: [MessageHandler],
  multi: true
};
