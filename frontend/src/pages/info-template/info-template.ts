import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { TaskProvider } from '../../providers/task/task';
import { FormGroup } from '@angular/forms/src/model';
import { FormBuilder, Validators} from '@angular/forms';

/**
 * Generated class for the InfoTemplatePage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-info-template',
  templateUrl: 'info-template.html',
})
export class InfoTemplatePage {

  task : TaskMapsModel;
  template : TaskTemplatesModel;
  fieldValueForm: FormGroup;

  constructor(public navCtrl: NavController, public navParams: NavParams, private service : TaskProvider, private formBuilder: FormBuilder) {
    this.fieldValueForm = this.formBuilder.group({
      name: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[A-Za-z]+([A-Za-z\\ ]*)$'), Validators.required])]});
    this.task = this.navParams.get('task');
    this.template = this.navParams.get('template');
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad InfoTemplatePage');
  }

  ionViewWillEnter(){

  }

  getKeys(){
    return Object.keys(this.template.fields);
  }

  getValues(field){
    return this.template.fields[field];
  }
}
