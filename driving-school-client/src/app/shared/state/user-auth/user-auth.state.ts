import { State, Action, Selector, StateContext } from '@ngxs/store';
import {AuthService} from '../../services/auth.service';
import {Login, Logout} from './user-auth.actions';
import {Injectable} from '@angular/core';
import {tap} from 'rxjs/operators';
import {IProfile} from '../../model/Profile';

export interface UserAuthStateModel {
  token: string | null;
  username: string | null;
  roles: string[];
  profile: IProfile;
}

@State<UserAuthStateModel>({
  name: 'userAuth',
  defaults: {
    token: null,
    username: null,
    roles: [],
    profile: null
  }
})

@Injectable()
export class UserAuthState {

  @Selector()
  static token(state: UserAuthStateModel): string | null {
    return state.token;
  }

  @Selector()
  static isAuthenticated(state: UserAuthStateModel): boolean {
    return !!state.token;
  }

  @Selector()
  static username(state: UserAuthStateModel): string | null {
    return state.username;
  }

  @Selector()
  static roles(state: UserAuthStateModel): string[] {
    return state.roles;
  }

  @Selector()
  static isShowAdminBoard(state: UserAuthStateModel): boolean {
    return state.roles?.includes('ADMIN');
  }

  @Selector()
  static isShowTeacherBoard(state: UserAuthStateModel): boolean {
    return state.roles?.includes('TEACHER');
  }

  @Selector()
  static isShowStudentBoard(state: UserAuthStateModel): boolean {
    return state.roles?.includes('STUDENT');
  }

  @Selector()
  static isGetProfile(state: UserAuthStateModel): IProfile {
    return state.profile;
  }

  constructor(private authService: AuthService) {}

  @Action(Login)
  login(ctx: StateContext<UserAuthStateModel>, action: Login): any {
    return this.authService.postLogin(action.payload.loginRequest).pipe(
      tap(
        result  =>  ctx.setState({
          token: result.token,
          username: result.username,
          roles: result.roles,
          profile: result.profile
        })
      ),
    );
  }

  @Action(Logout)
  logout(ctx: StateContext<UserAuthStateModel>): any {
    ctx.setState({
      token: null,
      username: null,
      roles: [],
      profile: null
    });
  }
}
