import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as Constants from '../../inner-resources/constants';
/*
  Generated class for the TaskProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class TaskProvider {

  constructor(public http: HttpClient) {
    console.log('Hello TaskProvider Provider');
  }

  getTaskMaps(){return this.http.get(Constants.BASE_URL_TASK_MAPS_API + '/taskMap');}

  getTemplatesTask(task){return this.http.get(Constants.BASE_URL_TASK_MAPS_API + '/taskMap/'+task)}
}
