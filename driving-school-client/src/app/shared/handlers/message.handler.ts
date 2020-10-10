import {APP_INITIALIZER, Injectable, Injector} from '@angular/core';
import {Actions, ofActionCanceled, ofActionCompleted, ofActionErrored, ofActionSuccessful, Store} from '@ngxs/store';
import {Login, Logout} from '../state/user-auth/user-auth.actions';
import {MessageService} from '../services/message.service';
import {UserAuthState} from '../state/user-auth/user-auth.state';

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
      .pipe(ofActionCompleted(Login))
      .subscribe(result => {
        const message = this.injector.get(MessageService);
        const username = store.selectSnapshot(UserAuthState.username);
        if (username != null) {
          message.showSuccess('Successful login as ' + username, 'Login');
        } else {
          message.showErrors('Error on login', 'Login');
        }
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
  }
}


const initFn = () => () => { /* use for some initialization stuff */ };

export const messageHandlerProviders = {
  provide: APP_INITIALIZER,
  useFactory: initFn,
  deps: [MessageHandler],
  multi: true
};
