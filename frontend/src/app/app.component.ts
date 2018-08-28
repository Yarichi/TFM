import { Component, ViewChild } from '@angular/core';
import { Platform, Nav } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { HomePage } from '../pages/home/home';
import { LoginPage } from '../pages/login/login';
import { TabPage } from '../pages/tab/tab'
import { IndoorSettingsPage } from '../pages/indoor-settings/indoor-settings';
@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;
  rootPage:any = LoginPage;
  pages: Array<{title : string, component: any}>;
  constructor(platform: Platform, statusBar: StatusBar, splashScreen: SplashScreen) {
    platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      statusBar.styleDefault();
      splashScreen.hide();
      this.pages = [
        {'title':'Indoor positioning settings', component: IndoorSettingsPage},
        {'title':'Home Page', component: TabPage},
      ]
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

