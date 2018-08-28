import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AppUsersProvider } from '../../providers/app-users/app-users'
import { Storage } from '@ionic/storage';

/**
 * Generated class for the HomePage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
})
export class HomePage {
  alias : String;
  token : String;
  storage : Storage;
  user : {};
  constructor(public navCtrl: NavController, public navParams: NavParams, private service : AppUsersProvider) {
    this.alias = localStorage.getItem('alias');
    this.token = localStorage.getItem('token');
    this.user = navParams.get('user');
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad HomePage');
  }

}
