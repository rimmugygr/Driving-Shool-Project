import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {authInterceptorProviders} from './shared/inceptors/auth.interceptor';
import {SharedModule} from './shared/shared.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {BrowserModule} from '@angular/platform-browser';
import {NotFoundPageComponent} from './pages/not-found-page/not-found-page.component';
import {NgxsModule} from '@ngxs/store';
import {NgxsStoragePluginModule, StorageOption} from '@ngxs/storage-plugin';
import {NgxsReduxDevtoolsPluginModule} from '@ngxs/devtools-plugin';
import {StateClass} from '@ngxs/store/internals';
import {environment} from '../environments/environment';
import {UserAuthState} from './shared/state/user-auth/user-auth.state';
import {routeHandlerProviders} from './shared/handlers/route.handler';
import {ToastrModule} from 'ngx-toastr';
import {messageHandlerProviders} from './shared/handlers/message.handler';
import {ProfilesStudentsListState} from './shared/state/profiles-students-list/profiles-students-list-state.service';
import {ProfilesTeachersListState} from './shared/state/profiles-teachers-list/profiles-teachers-list-state.service';
import {AvailableDateListState} from './shared/state/available-date-list/available-date-list.state';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';

const persistentStates: StateClass<any>[] = [UserAuthState];
const states: StateClass<any>[] = [...persistentStates, ProfilesStudentsListState, ProfilesTeachersListState, AvailableDateListState];

@NgModule({
  declarations: [
    AppComponent,
    NotFoundPageComponent
  ],
  imports: [
    AppRoutingModule,
    HttpClientModule,
    SharedModule,
    BrowserAnimationsModule,
    BrowserModule,
    ToastrModule.forRoot(),
    NgxsModule.forRoot(states, {developmentMode: !environment.production}),
    NgxsStoragePluginModule.forRoot({ key: persistentStates, storage: StorageOption.LocalStorage}),
    NgxsReduxDevtoolsPluginModule.forRoot({disabled: environment.production}),
  ],
  providers: [
    authInterceptorProviders,
    routeHandlerProviders,
    messageHandlerProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
