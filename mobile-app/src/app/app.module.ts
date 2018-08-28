import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { MissionsPage } from '../pages/missions/missions';
import { FriendsPage } from '../pages/friends/friends';
import { ProfilePage } from '../pages/profile/profile';
import { AppUsersProvider } from '../providers/app-users/app-users';
import { MissionsProvider } from '../providers/missions/missions';
import { FormContactPage } from '../pages/form-contact/form-contact';
import { AddFriendPage } from '../pages/add-friend/add-friend';
import { LoginPage } from '../pages/login/login';
import { AuthProvider } from '../providers/auth/auth'
import { HttpClient, HttpHandler, HttpClientModule } from '@angular/common/http';
import { IonicStorageModule } from '@ionic/storage';
import { AuthInterceptorProvider } from '../providers/auth-interceptor/auth-interceptor';

import {HTTP_INTERCEPTORS} from '@angular/common/http';
import { RegisterPage } from '../pages/register/register';
import { InfoMissionPage } from '../pages/info-mission/info-mission';
import { MapMissionPage } from '../pages/map-mission/map-mission';
import { LocationProvider } from '../providers/location/location';
import { NetworkInterface } from '@ionic-native/network-interface';


@NgModule({
  declarations: [
    MyApp,
    HomePage,
    MissionsPage,
    FriendsPage,
    ProfilePage,
    FormContactPage,
    LoginPage,
    AddFriendPage,
    RegisterPage,
    MissionsPage,
    InfoMissionPage,
    MapMissionPage
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    IonicModule.forRoot(MyApp),
    IonicStorageModule.forRoot()
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    MissionsPage,
    FriendsPage,
    ProfilePage,
    FormContactPage,
    LoginPage,
    AddFriendPage,
    RegisterPage,
    MissionsPage,
    InfoMissionPage,
    MapMissionPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    AppUsersProvider,
    MissionsProvider,
    AuthProvider,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorProvider,
      multi: true,
    },
    LocationProvider,
    NetworkInterface,
  ]
})
export class AppModule {}
