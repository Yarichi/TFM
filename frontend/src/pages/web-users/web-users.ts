import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { WebuserService } from '../../providers/webuser-service/webuser-service';
import { InfoWebuserPage } from '../info-webuser/info-webuser';
import { FormWebuserPage } from '../form-webuser/form-webuser';
import { FormBuilder } from '@angular/forms';

/**
 * Generated class for the WebUsersPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-web-users',
  templateUrl: 'web-users.html',
})
export class WebUsersPage {

  webusers: Array<any>;
  formWebUser = FormWebuserPage;

  constructor(public navCtrl: NavController, public navParams: NavParams, private service: WebuserService) {
  }

  allWebUsers() {
    this.service.allWebUsers().subscribe(
      (data: Array<any>) => {
        this.webusers = data;
      },
      err => {
        console.log(err);
      },
      () => console.log('Initial Load WebUsers')
    );
  }

  ionViewWillEnter(){
    this.allWebUsers();
  }

  searchForWebUser(event, key) {
    this.service.searchWebUsers(event.target.value).subscribe(
      (data: Array<any>) => {
        this.webusers = data;
      },
      err => {
        console.log(err);
      },
      () => console.log('WebUser Search Complete')
    );
  }

  selectWebUser(event, user){
    this.navCtrl.push(InfoWebuserPage, {
      user: user
    })
  }

  updateWebUser(event, user){
    this.navCtrl.push(FormWebuserPage, {
      user: user
    })
  }

  deleteWebUser(event, user){
    this.service.removeWebUser(user.userId);
  }

}


