import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AppuserService } from '../../providers/appuser-service/appuser-service';
import { InfoAppuserPage } from '../info-appuser/info-appuser';

/**
 * Generated class for the AppUsersPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-app-users',
  templateUrl: 'app-users.html',
})
export class AppUsersPage {
  appusers: Array<any>;
  constructor(public navCtrl: NavController, public navParams: NavParams, private service: AppuserService) {
  }

  ionViewWillEnter(){
    this.allAppUsers();
  }

  allAppUsers() {
    this.service.allAppUsers().subscribe(
      (data: Array<any>) => {
        this.appusers = data;
      },
      err => {
        console.log(err);
      },
      () => console.log('Initial Load AppUsers')
    );
  }

  searchForAppUser(event, key) {
    this.service.searchAppUsers(event.target.value).subscribe(
      (data: Response) => {
        console.log(data);
        this.appusers = data.arrayBuffer.arguments; 
        console.log(data);
      },
      err => {
        console.log(err);
      },
      () => console.log('AppUser Search Complete')
    );
  }

  selectAppUser(event, user){
    console.log(user);
    this.navCtrl.push(InfoAppuserPage, {
      user: user
    })
  }

  removeAppUser(event, user){
    this.service.removeAppUser(user['userId']);
    this.allAppUsers();
  }
}


