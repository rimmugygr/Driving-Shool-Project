import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Store} from '@ngxs/store';
import {UserAuthState} from '../state/user-auth/user-auth.state';

const TOKEN_HEADER_KEY = 'Authorization';
const TOKEN_PREFIX_VALUE = 'Bearer ';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private store: Store) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authRequest = request;
    const token = this.store.selectSnapshot(UserAuthState.token);
    if (token != null) {
      authRequest = request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, TOKEN_PREFIX_VALUE + token) });
    }
    return next.handle(authRequest);
  }
}

export const authInterceptorProviders = {
  provide: HTTP_INTERCEPTORS,
  useClass: AuthInterceptor,
  multi: true
};
