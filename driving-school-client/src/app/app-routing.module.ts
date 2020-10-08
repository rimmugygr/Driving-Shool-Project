import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuard} from './shared/guards/auth.guard';
import {NotFoundPageComponent} from './pages/not-found-page/not-found-page.component';

const routes: Routes = [
  {
    path: `home`,
    loadChildren: () => import('./pages/home-page/home-page.module').then(m => m.HomePageModule)
  },
  {
    path: 'student',
    loadChildren: () => import('./pages/student-page/student-page.module').then(m => m.StudentPageModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'teacher',
    loadChildren: () => import('./pages/teacher-page/teacher-page.module').then(m => m.TeacherPageModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'available',
    loadChildren: () => import('./pages/available-date-page/available-date-page.module').then(m => m.AvailableDatePageModule),
    canActivate: [AuthGuard]
  },
  {
    path: ``,
    redirectTo: `home`,
    pathMatch: `full`
  },
  {
    path: '**',
    component: NotFoundPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
