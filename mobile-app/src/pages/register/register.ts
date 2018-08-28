import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FormGroup } from '@angular/forms/src/model';
import { FormBuilder, Validators} from '@angular/forms';
import { AppUsersProvider } from '../../providers/app-users/app-users';

/**
 * Generated class for the RegisterPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-register',
  templateUrl: 'register.html',
})
export class RegisterPage {

  registerForm: FormGroup;
  constructor(public navCtrl: NavController, 
            public navParams: NavParams,
            private formBuilder: FormBuilder,
            private service: AppUsersProvider) {
    this.registerForm = this.formBuilder.group({
      alias: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[A-Za-z]+([A-Za-z\\ ]*)$'), Validators.required])],
      type_template: ['', ''],
      role: ['', ''],
      phone: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[0-9]{9}$'), Validators.required])],
      telegramAlias: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[@_A-Za-z0-9-\\+]+([@_A-Za-z0-9-\\+]*)$'), Validators.required])],
      mail: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$'), Validators.required])],
      password: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[@_A-Za-z0-9-\\+]+([@_A-Za-z0-9-\\+]*)$'), Validators.required])]
    });
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad RegisterPage');
  }

  onRegister(){
    new Promise(resolve => {
      this.service.addAppUser(this.registerForm.value).then(data => {
        this.navCtrl.pop();
      });
    });
  }
}
