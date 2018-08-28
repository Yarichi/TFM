import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as Constants from '../../inner-resources/constants';
/*
  Generated class for the RoutineServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class RoutineService {

  constructor(public http: HttpClient) {
    console.log('Hello RoutineServiceProvider Provider');
  }

  searchRoutines(routine){
    return this.http.get(Constants.BASE_URL_TASK_MAPS_API + '/routines')
  }

  addRoutine(data, tasks){
    tasks.forEach(element => {
      element['start_date'] = new Date(data['date_routine'] + " " + element['start_date']).getTime() / 1000;
      element['final_date'] = new Date(data['date_routine'] + " " + element['final_date']).getTime() / 1000;
    });
    for(var i = 0; i < tasks.length; i++){
      tasks[i]['order'] = i
    }
    var date = new Date(data['date_routine']);
    data['date_routine'] = date.getTime() / 1000;
    var month = date.getMonth() + 1;
    data['tasks'] = tasks;
    data['id'] = data['alias_user'] + '_' + date.getDate() + '_' + month + '_' + date.getFullYear()
  
    return new Promise((resolve, reject) => {
      this.http.post(Constants.BASE_URL_TASK_MAPS_API + '/routines', JSON.stringify(data), {headers:{'Content-Type':'application/json'}})
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }

  modifyRoutine(routineId, data){
    return new Promise((resolve, reject) => {
      this.http.put(Constants.BASE_URL_TASK_MAPS_API + '/routines/'+routineId, JSON.stringify(data), {headers:{'Content-Type':'application/json'}})
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }

  removeRoutine(routineId){
    return new Promise((resolve, reject) => {
      this.http.delete(Constants.BASE_URL_TASK_MAPS_API + '/routines/'+routineId)
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }

  allRoutines(){
    return this.http.get(Constants.BASE_URL_TASK_MAPS_API + '/routines')
  }
}
