export interface IColor {
  primary: string;
  secondary: string;
  id?: string;
}

export const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  } as IColor ,
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  } as IColor ,
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  } as IColor ,
};
