import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as Constants from '../../inner-resources/constants'
/*
  Generated class for the AppuserServiceProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class AppuserService {

  constructor(public http: HttpClient) {
    console.log('Hello AppuserServiceProvider Provider');
  }

  allAppUsers(){
    return this.http.get(Constants.BASE_URL_APP_USER_API + '/appUser');
  }

  searchAppUsers(username){
    return this.http.get(Constants.BASE_URL_APP_USER_API + '/appUser');
  }

  addAppUser(data){
    return new Promise((resolve, reject) => {
      this.http.post(Constants.BASE_URL_APP_USER_API + '/appUser', JSON.stringify(data))
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }

  modifyAppUser(userId, data){
    return new Promise((resolve, reject) => {
      this.http.put(Constants.BASE_URL_APP_USER_API + '/appUser/'+userId, JSON.stringify(data))
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }

  removeAppUser(userId){
    return new Promise((resolve, reject) => {
      this.http.delete(Constants.BASE_URL_APP_USER_API + '/appUser/'+userId)
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }
}
