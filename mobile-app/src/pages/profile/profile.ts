import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import {FormContactPage} from '../form-contact/form-contact'
import { AppUsersProvider } from '../../providers/app-users/app-users'

/**
 * Generated class for the ProfilePage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-profile',
  templateUrl: 'profile.html',
})
export class ProfilePage {
  alias: String;
  user: any;
  constructor(public navCtrl: NavController, public navParams: NavParams, private service : AppUsersProvider) {
    this.alias = localStorage.getItem('alias');
    this.user = navParams.get('user');
    this.loadUser();
  }
  ionViewWillEnter(){
    this.loadUser();
  }
  ionViewDidLoad() {
    console.log('ionViewDidLoad ProfilePage');
  }

  loadUser(){
  return new Promise(resolve => {
    this.service.searchAppUser(this.alias).subscribe(data => {
      this.user = data;
    })
  });
  }

  onUpdateUser(){
    this.navCtrl.push(FormContactPage, {'user': this.user});
  }
}
