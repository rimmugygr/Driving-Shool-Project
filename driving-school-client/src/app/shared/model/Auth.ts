export class  LoginResponse{
  userId: number;
  username: string;
  token: string;
  roles: string[];
  profile: any;
}
export class  LoginRequest{
  username: string;
  password: string;
}
