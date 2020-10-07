import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import {Store} from '@ngxs/store';
import {UserAuthState} from '../state/user-auth/user-auth.state';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private store: Store) {
  }

  canActivate( next: ActivatedRouteSnapshot,
               state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.store.selectSnapshot(UserAuthState.isAuthenticated);
  }

}
