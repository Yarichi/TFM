import { Component, ViewChild } from '@angular/core';
import { Platform, Nav } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { HomePage } from '../pages/home/home';
import { ProfilePage } from '../pages/profile/profile';
import { MissionsPage } from '../pages/missions/missions';
import { FriendsPage } from '../pages/friends/friends';
import { LoginPage } from '../pages/login/login';
@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;
  alias:String;
  token:String;
  rootPage:any = LoginPage;
  pages: Array<{title : string, component: any}>;
  constructor(platform: Platform, statusBar: StatusBar, splashScreen: SplashScreen) {
    this.alias = this.rootPage.alias;
    this.token = this.rootPage.token;
    this.pages = [
      {'title':'Home', component: HomePage},
      {'title':'Profile', component: ProfilePage},
      {'title':'Missions', component: MissionsPage},
      {'title':'Friends', component: FriendsPage}
    ]

    platform.ready().then(() => {
      statusBar.styleDefault();
      splashScreen.hide();
    });
  }

  openPage(page) {
    this.nav.setRoot(page.component, {user: this.rootPage.user, alias: this.rootPage.alias, token: this.rootPage.token});
  }

  logout(){
    localStorage.clear();
    this.nav.setRoot(LoginPage);
  }

}

