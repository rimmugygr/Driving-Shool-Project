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

const persistentStates: StateClass<any>[] = [UserAuthState];
const states: StateClass<any>[] = [...persistentStates];

@NgModule({
  declarations: [
    AppComponent,
    NotFoundPageComponent,
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
    NgxsReduxDevtoolsPluginModule.forRoot({disabled: environment.production})
  ],
  providers: [
    authInterceptorProviders,
    routeHandlerProviders,
    messageHandlerProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
