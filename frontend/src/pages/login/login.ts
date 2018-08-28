import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, Loading, AlertController, LoadingController, Tab} from 'ionic-angular';
import {AuthProvider} from '../../providers/auth/auth';
import { TabPage } from '../tab/tab';
import { WebuserService } from '../../providers/webuser-service/webuser-service';

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
  registerCredentials = { mail: '', password: '' };
  token: any;
 
  constructor(private nav: NavController, private auth: AuthProvider, private alertCtrl: AlertController, private loadingCtrl: LoadingController, private webService : WebuserService) { }
 
  public createAccount() {
    this.nav.push('RegisterPage');
  }
 
  public login() {
    this.showLoading()
    this.auth.login(this.registerCredentials).subscribe(
      data => this.loadData(data));
    ;
  }
 
  loadData(data){
    localStorage.setItem('mail', this.registerCredentials['mail']);
    localStorage.setItem('token', data['detail']);
    new Promise(resolve => {
      this.webService.searchWebUser(this.registerCredentials['mail'])
      .subscribe(data => {
        this.nav.setRoot(TabPage, {'user' : data});
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