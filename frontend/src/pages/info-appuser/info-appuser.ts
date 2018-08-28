import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AppuserService } from '../../providers/appuser-service/appuser-service';

/**
 * Generated class for the InfoAppuserPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-info-appuser',
  templateUrl: 'info-appuser.html',
})
export class InfoAppuserPage {

  user: {};
  constructor(public navCtrl: NavController, public navParams: NavParams, private service: AppuserService) {
    this.user = navParams.get('user');
  }

  onRemoveUser(){
    this.service.removeAppUser(this.user['userId']);
    this.navCtrl.pop();
  }


}
