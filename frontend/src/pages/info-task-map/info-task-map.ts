import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { TaskProvider } from '../../providers/task/task';
import { INTERNAL_BROWSER_DYNAMIC_PLATFORM_PROVIDERS } from '@angular/platform-browser-dynamic/src/platform_providers';
import { InfoTemplatePage } from '../info-template/info-template';

/**
 * Generated class for the InfoTaskMapPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-info-task-map',
  templateUrl: 'info-task-map.html',
})
export class InfoTaskMapPage {

  task : TaskMapsModel;
  templates : Array<TaskTemplatesModel>;
  constructor(public navCtrl: NavController, public navParams: NavParams, private service : TaskProvider) {
    this.task = this.navParams.get("task");
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad InfoTaskMapPage');
  }

  ionViewWillEnter(){
    this.service.getTemplatesTask(this.task.name).subscribe((data : Array<TaskTemplatesModel> ) => this.templates = data);
  }

  getFields(template : TaskTemplatesModel){
    return Object.keys(template.fields);
  }

  getValue(template, field){
    return template.fields[field].length;
  }

  selectTemplate(event, template){
    this.navCtrl.push(InfoTemplatePage, {'task': this.task, 'template': template})
  }
}
