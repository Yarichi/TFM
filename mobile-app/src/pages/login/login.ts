import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, Loading, AlertController, LoadingController} from 'ionic-angular';
import {AuthProvider} from '../../providers/auth/auth';
import { HomePage } from '../../pages/home/home';
import { Storage } from '@ionic/storage';
import { AppUsersProvider } from '../../providers/app-users/app-users';
import { RegisterPage } from '../register/register';

/**
 * Generated class for the LoginPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {
  loading: Loading;
  registerCredentials = { alias: '', password: '' };
  registerPage = RegisterPage;
  constructor(private nav: NavController, private auth: AuthProvider, private alertCtrl: AlertController, private loadingCtrl: LoadingController, private storage : Storage, private appService : AppUsersProvider) { }
 
  public createAccount() {
    this.nav.push('RegisterPage');
  }
 
  public login() {
    this.showLoading();
    this.auth.login(this.registerCredentials).subscribe(
      data => this.loadData(data));
    ;
  }

  loadData(data){
    localStorage.setItem('alias', this.registerCredentials['alias']);
    localStorage.setItem('token', data['detail']);
    new Promise(resolve => {
      this.appService.searchAppUser(this.registerCredentials['alias'])
      .subscribe(data => {
        this.nav.setRoot(HomePage, {'user' : data});
      })
    })
  }
 
  showLoading() {
    this.loading = this.loadingCtrl.create({
      content: 'Please wait...',
      dismissOnPageChange: true
    });
    this.loading.present();
  }
 
  showError(text) {
    this.loading.dismiss();
 
    let alert = this.alertCtrl.create({
      title: 'Fail',
      subTitle: text,
      buttons: ['OK']
    });
  }
}