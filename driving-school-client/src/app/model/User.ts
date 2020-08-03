export class  User{
  id: number;
  loginName: string;
  password: string;
  createDate: string;
  updateDate: string;


  constructor(id: number, loginName: string, password: string) {
    this.id = id;
    this.loginName = loginName;
    this.password = password;
  }
}
