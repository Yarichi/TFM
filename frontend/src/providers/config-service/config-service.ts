import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as Constants from '../../inner-resources/constants';
/*
  Generated class for the ConfigServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class ConfigServiceProvider {

  constructor(public http: HttpClient) {
    console.log('Hello ConfigServiceProvider Provider');
  }

  setSettings(data){
    return new Promise((resolve, reject) => {
      console.log(Constants.BASE_URL_LOCATION_API + '/config');
      this.http.post(Constants.BASE_URL_LOCATION_API + '/config', JSON.stringify(data), {headers:{'Content-Type':'application/json'}})
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }


  getSettings(){
    return this.http.get(Constants.BASE_URL_LOCATION_API + '/config');
  }

}
