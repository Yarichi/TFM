import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { TaskProvider } from '../../providers/task/task';
import { InfoTaskMapPage } from '../info-task-map/info-task-map';

/**
 * Generated class for the TaskMapsPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-task-maps',
  templateUrl: 'task-maps.html',
})
export class TaskMapsPage {

  tasks : Array<TaskMapsModel>;
  constructor(public navCtrl: NavController, public navParams: NavParams, private service : TaskProvider) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad TaskMapsPage');
  }

  ionViewWillEnter(){
    this.service.getTaskMaps().subscribe((data : Array<TaskMapsModel>)=> this.tasks = data);
  }

  selectTask(event, task){
    this.navCtrl.push(InfoTaskMapPage, {'task':task});
  }
}
