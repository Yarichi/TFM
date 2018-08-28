import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { WebuserService } from '../../providers/webuser-service/webuser-service';
import { FormWebuserPage } from '../form-webuser/form-webuser';


/**
 * Generated class for the InfoWebuserPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-info-webuser',
  templateUrl: 'info-webuser.html',
})
export class InfoWebuserPage {

  user: {};
  formWebUser = FormWebuserPage;
  constructor(public navCtrl: NavController, public navParams: NavParams, private service: WebuserService) {
    this.user = navParams.get('user');
  }

  onRemoveUser(){
    this.service.removeWebUser(this.user['userId']);
  }


}
