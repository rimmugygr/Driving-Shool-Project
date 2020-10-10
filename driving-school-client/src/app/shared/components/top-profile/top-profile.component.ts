import {Component, Input, OnInit} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {LoginModalComponent} from '../login-modal/login-modal.component';
import {UserAuthState} from '../../state/user-auth/user-auth.state';
import {Select, Store} from '@ngxs/store';
import {Observable} from 'rxjs';
import {Logout} from '../../state/user-auth/user-auth.actions';

@Component({
  selector: 'app-top-profile',
  templateUrl: './top-profile.component.html',
  styleUrls: ['./top-profile.component.css']
})
export class TopProfileComponent implements OnInit {
  @Input()
  placeName: string;

  @Select(UserAuthState.isAuthenticated)
  isLoggedIn$: Observable<boolean>;

  @Select(UserAuthState.username)
  username$: Observable<string>;

  @Select(UserAuthState.roles)
  roles$: Observable<string[]>;

  constructor(private modalService: NgbModal,
              private store: Store) { }

  ngOnInit(): void {
  }

  openLoginModal(): void {
    this.modalService.open(LoginModalComponent);
  }

  logout(): void {
    this.store.dispatch(new Logout());
  }
}
