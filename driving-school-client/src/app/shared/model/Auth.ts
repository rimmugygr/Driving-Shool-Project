export class  LoginResponse{
  id: number;
  username: string;
  token: string;
  roles: string[];
}
export class  LoginRequest{
  username: string;
  password: string;
}
