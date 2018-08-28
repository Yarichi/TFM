import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as Constants from '../../inner-resources/constants';

/*
  Generated class for the WebuserServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class WebuserService {
  constructor(public http: HttpClient) {
    console.log('Hello WebuserService Provider');
  }

  allWebUsers(){
    return this.http.get(Constants.BASE_URL_WEB_USER_API + '/webUser');
  }

  allWebUsersSelect(){
    return this.http.get(Constants.BASE_URL_WEB_USER_API + '/webUser/select');
  }

  searchWebUsers(mail){
    return this.http.get(Constants.BASE_URL_WEB_USER_API + '/webUser');
  }

  searchWebUser(mail){
    return this.http.get(Constants.BASE_URL_WEB_USER_API + '/webUser/' + mail + '/mail')
  }


  addWebUser(data){
    return new Promise((resolve, reject) => {
      this.http.post(Constants.BASE_URL_WEB_USER_API + '/webUser', JSON.stringify(data), {headers:{'Content-Type':'application/json'}})
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }

  modifyWebUser(id, data){
    return new Promise((resolve, reject) => {
      this.http.put(Constants.BASE_URL_WEB_USER_API + '/webUser/' + id, JSON.stringify(data), {headers:{'Content-Type':'application/json'}})
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }

  removeWebUser(id){
    return new Promise((resolve, reject) => {
      this.http.delete(Constants.BASE_URL_WEB_USER_API + '/webUser/' + id, {headers:{'Content-Type':'application/json'}})
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }

}
