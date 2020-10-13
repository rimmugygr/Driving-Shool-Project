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
    loadChildren: () => import('./pages/profiles-students-page/profiles-students-page.module').then(m => m.ProfilesStudentsPageModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'teacher',
    loadChildren: () => import('./pages/profiles-teachers-page/profiles-teachers-page.module').then(m => m.ProfilesTeachersPageModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'available',
    loadChildren: () => import('./pages/available-date-page/available-date-page.module').then(m => m.AvailableDatePageModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'profile',
    loadChildren: () => import('./pages/profile-page/profile-page.module').then(m => m.ProfilePageModule),
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
