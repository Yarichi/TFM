import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FormGroup } from '@angular/forms/src/model';
import { FormBuilder, Validators} from '@angular/forms';
import { WebuserService } from '../../providers/webuser-service/webuser-service';

/**
 * Generated class for the FormWebuserPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-form-webuser',
  templateUrl: 'form-webuser.html',
})
export class FormWebuserPage {


  credentialsForm: FormGroup;
  user: {};
  isNPC: Boolean;
  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              private formBuilder: FormBuilder,
              private service: WebuserService) {
    this.credentialsForm = this.formBuilder.group({
      name: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[A-Za-z]+([A-Za-z\\ ]*)$'), Validators.required])],
      lastName: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[A-Za-z]+([A-Za-z\\ ]*)$'), Validators.required])],
      isNPC: ['', ''],
      phone: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[0-9]{9}$'), Validators.required])],
      telegramAlias: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[@_A-Za-z0-9-\\+]+([@_A-Za-z0-9-\\+]*)$')])],
      occupation: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[A-Za-z]+([A-Za-z\\ ]*)$'), Validators.required])],
      mail: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$'), Validators.required])],
      password: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[@_A-Za-z0-9-\\+]+([@_A-Za-z0-9-\\+]*)$'), Validators.required])]
    });
    ;
    if(navParams.get('user') != undefined){
      this.user = navParams.get('user');
      this.credentialsForm.setValue({'name':this.user['name'],
                                    'lastName':this.user['lastName'],
                                    'phone':this.user['phone'],
                                    'telegramAlias':this.user['telegramAlias'],
                                    'occupation':this.user['occupation'],
                                    'mail':this.user['mail'],
                                    'password':this.user['password']});
    }
    console.log(this.user);
  }

  onAddUser(){
    this.service.addWebUser(this.credentialsForm.value);
  }

  onUpdateUser(){
    this.service.modifyWebUser(this.user['userId'], this.credentialsForm.value);
  }

  onRemoveUser(){
    this.service.removeWebUser(this.credentialsForm['id']);
  }
  ionViewDidLoad() {
    console.log('ionViewDidLoad FormAppuserPage');
  }

}
