import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FormGroup } from '@angular/forms/src/model';
import { FormBuilder, Validators} from '@angular/forms';
import { AppUsersProvider } from '../../providers/app-users/app-users';

/**
 * Generated class for the FormContactPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-form-contact',
  templateUrl: 'form-contact.html',
})
export class FormContactPage {

  contactForm: FormGroup;
  user: {};
  constructor(public navCtrl: NavController, public navParams: NavParams, private formBuilder: FormBuilder, private service : AppUsersProvider) {
    this.user = this.navParams.get('user');
    this.contactForm = this.formBuilder.group({
      alias: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[@_A-Za-z0-9-\\+]+([@_A-Za-z0-9-\\+]*)$'), Validators.required])],
      mail: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$'), Validators.required])],
      phone: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[0-9]{9}$'), Validators.required])],
      telegramAlias: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[@_A-Za-z0-9-\\+]+([@_A-Za-z0-9-\\+]*)$'), Validators.required])],
    });
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad FormContactPage');
  }

  onUpdateUser(){
    new Promise(resolve => {
      this.service.modifyAppUser(localStorage.getItem('alias'), this.contactForm.value)
      .then(data => {this.navCtrl.pop();});
    });
  }
}
