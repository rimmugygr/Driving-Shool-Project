import {APP_INITIALIZER, Injectable} from '@angular/core';
import {Actions, ofActionSuccessful} from '@ngxs/store';
import {Logout} from '../state/user-auth/user-auth.actions';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouteHandler {

  constructor(
    private actions$: Actions,
    private router: Router
  ) {
    // go login if logout success
    this.actions$.pipe(ofActionSuccessful(Logout)).subscribe(result => {
      this.router.navigateByUrl('login');
    });
  }
}

const initFn = () => () => { /* use for some initialization stuff */ };

export const routeHandlerProviders = {
  provide: APP_INITIALIZER,
  useFactory: initFn,
  deps: [RouteHandler],
  multi: true
};

