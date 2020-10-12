export class  AvailableDate implements IAvailableDate{
  id: string;
  date: string;
  teacherId: string;
  reserved: boolean;
  teacherFirstName: string;
  teacherLastName: string;
}
export interface IAvailableDate {
  id: string;
  date: string;
  teacherId: string;
  reserved: boolean;
  teacherFirstName: string;
  teacherLastName: string;
}
