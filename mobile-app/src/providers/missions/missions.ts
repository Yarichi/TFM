import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as Constants from '../../inner-resources/constants';

/*
  Generated class for the AppuserServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class MissionsProvider {

  constructor(public http: HttpClient) {
    console.log('Hello AppuserServiceProvider Provider');
  }
  getMissions(){
    var now = new Date();
    var data = {'username': 'string', 'day': '15', 'month': now.getMonth() + 1, 'year': now.getFullYear()};
    return new Promise((resolve, reject) => {
      this.http.post(Constants.BASE_URL_ROUTINES_API + '/routines/missions', JSON.stringify(data), {headers:{'Content-Type':'application/json'}})
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }
}
