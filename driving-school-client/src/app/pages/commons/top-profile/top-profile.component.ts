import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from '../../../services/auth/auth.service';
import {TokenStorageService} from '../../../services/auth/token-storage.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {LoginModalComponent} from '../login-modal/login-modal.component';

@Component({
  selector: 'app-top-profile',
  templateUrl: './top-profile.component.html',
  styleUrls: ['./top-profile.component.css']
})
export class TopProfileComponent implements OnInit {
  @Input()
  placeName: string;

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  username = '';

  constructor(private auth: AuthService,
              private tokenStorage: TokenStorageService,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
      this.username = this.tokenStorage.getUser().username;
    }
  }

  openLoginModal(): void {
    const modalRef = this.modalService.open(LoginModalComponent);
  }

  logout(): void {
    this.auth.logout();
  }
}
