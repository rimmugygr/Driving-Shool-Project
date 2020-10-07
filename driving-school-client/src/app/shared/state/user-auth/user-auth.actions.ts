import {LoginRequest} from '../../model/Auth';

export class Login {
  static readonly type = '[UserAuth] Login';
  constructor(public payload: { loginRequest: LoginRequest } ) {}
}

export class Logout {
  static readonly type = '[UserAuth] Logout';
}
