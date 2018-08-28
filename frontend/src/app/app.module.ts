 import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { HttpClientModule } from '@angular/common/http';
import { WebuserService } from '../providers/webuser-service/webuser-service';
import { AppuserService } from '../providers/appuser-service/appuser-service';
import { RoutineService } from '../providers/routine-service/routine-service';
import { AppUsersPage } from '../pages/app-users/app-users';
import { WebUsersPage } from '../pages/web-users/web-users';
import { HttpClient } from '@angular/common/http/src/client';
import { RoutinesPage } from '../pages/routines/routines';
import { FormWebuserPage } from '../pages/form-webuser/form-webuser';
import { FormAppuserPage } from '../pages/form-appuser/form-appuser';
import { FormRoutinePage } from '../pages/form-routine/form-routine';
import { TabPage } from '../pages/tab/tab';
import { InfoWebuserPage } from '../pages/info-webuser/info-webuser';
import { InfoAppuserPage } from '../pages/info-appuser/info-appuser';
import { InfoRoutinePage } from '../pages/info-routine/info-routine';
import { LoginPage } from '../pages/login/login';
import { AuthProvider } from '../providers/auth/auth';

import { AuthInterceptorProvider } from '../providers/auth-interceptor/auth-interceptor';

import {HTTP_INTERCEPTORS} from '@angular/common/http';
import { TaskProvider } from '../providers/task/task';
import { TaskMapsPage } from '../pages/task-maps/task-maps';
import { InfoTaskMapPage } from '../pages/info-task-map/info-task-map';
import { InfoTemplatePage } from '../pages/info-template/info-template';
import { IndoorSettingsPage } from '../pages/indoor-settings/indoor-settings';
import { ConfigServiceProvider } from '../providers/config-service/config-service';
import { LocationProvider } from '../providers/location/location';

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    WebUsersPage,
    FormWebuserPage,
    AppUsersPage,
    FormAppuserPage,
    RoutinesPage,
    FormRoutinePage,
    InfoWebuserPage,
    InfoAppuserPage,
    InfoRoutinePage,
    TabPage,
    TaskMapsPage,
    InfoTaskMapPage,
    InfoTemplatePage,
    IndoorSettingsPage,
    LoginPage
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    IonicModule.forRoot(MyApp, {tabsPlacement: 'top'})
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    WebUsersPage,
    FormWebuserPage,
    AppUsersPage,
    FormAppuserPage,
    RoutinesPage,
    FormRoutinePage,
    FormRoutinePage,
    InfoWebuserPage,
    InfoAppuserPage,
    InfoRoutinePage,
    TaskMapsPage,
    TabPage,
    InfoTaskMapPage,
    InfoTemplatePage,
    IndoorSettingsPage,
    LoginPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    WebuserService,
    AppuserService,
    RoutineService,
    AuthProvider,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorProvider,
      multi: true,
    },
    TaskProvider,
    ConfigServiceProvider,
    LocationProvider
  ]
})
export class AppModule {}
