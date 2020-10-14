import { Component, OnInit } from '@angular/core';
import {Select} from '@ngxs/store';
import {UserAuthState} from '../../shared/state/user-auth/user-auth.state';
import {Observable} from 'rxjs';
import {IProfile} from '../../shared/model/Profile';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
  placeName = `Profile`;
  @Select(UserAuthState.isGetProfile)
  profile$: Observable<IProfile>;

  constructor() { }

  ngOnInit(): void {
  }

}
