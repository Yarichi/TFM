import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { WebUsersPage } from '../web-users/web-users';
import { AppUsersPage } from '../app-users/app-users';
import { HomePage } from '../home/home';
import { RoutinesPage } from '../routines/routines';
import { TaskMapsPage } from '../task-maps/task-maps';

/**
 * Generated class for the TabPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-tab',
  templateUrl: 'tab.html',
})
export class TabPage {

  homePage = HomePage;
  webPage = WebUsersPage;
  appPage = AppUsersPage;
  routinePage = RoutinesPage;
  taskMapPage = TaskMapsPage;

  user: any;
  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.user = navParams.get('user');
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad TabPage');
  }

}
