import { Component, OnInit } from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {LoginRequest} from '../../model/Auth';
import {Select, Store} from '@ngxs/store';
import {UserAuthState} from '../../state/user-auth/user-auth.state';
import {Observable} from 'rxjs';
import {Login} from '../../state/user-auth/user-auth.actions';

@Component({
  selector: 'app-login-modal',
  templateUrl: './login-modal.component.html',
  styleUrls: ['./login-modal.component.css']
})
export class LoginModalComponent implements OnInit {

  formLogin: LoginRequest = new LoginRequest();

  @Select(UserAuthState.isAuthenticated)
  isLoggedIn$: Observable<boolean>;

  @Select(UserAuthState.roles)
  roles$: Observable<string[]>;

  constructor( private modal: NgbActiveModal,
               private store: Store) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.store.dispatch(new Login( {  loginRequest: this.formLogin } ));
    this.modal.close();
  }
}
