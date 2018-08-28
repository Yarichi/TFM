import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FormGroup } from '@angular/forms/src/model';
import { FormBuilder, Validators} from '@angular/forms';
import { AppuserService } from '../../providers/appuser-service/appuser-service';

/**
 * Generated class for the FormAppuserPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-form-appuser',
  templateUrl: 'form-appuser.html',
})
export class FormAppuserPage {

  inputForm: FormGroup;
  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              private formBuilder: FormBuilder,
              private service: AppuserService) {
    this.inputForm = this.formBuilder.group({
      alias: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[@_A-Za-z0-9-\\+]+([@_A-Za-z0-9-\\+]*)$'), Validators.required])],
      level: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[0-9]+$'), Validators.required])],
      type_template: [''],
      role: [''],
      mail: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$'), Validators.required])],
      phone: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[0-9]{9}$'), Validators.required])],
      telegramAlias: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[@_A-Za-z0-9-\\+]+([@_A-Za-z0-9-\\+]*)$'), Validators.required])],
      position: ['']
    });
  }

  onAddUser(){
    this.service.addAppUser(this.inputForm);
  }

  onUpdateUser(){
    this.service.modifyAppUser(this.inputForm['userId'], this.inputForm);
  }

  onRemoveUser(){
    this.service.removeAppUser(this.inputForm['userId']);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad FormAppuserPage');
  }

}
