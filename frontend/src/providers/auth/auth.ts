import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as Constants from '../../inner-resources/constants';
/*
  Generated class for the AuthProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class AuthProvider {
  constructor(public http: HttpClient) {
    console.log('Hello AuthProvider Provider');
  }
  login(data){
    return this.http.post(Constants.BASE_URL_WEB_USER_API+'/login', JSON.stringify(data), {headers:{'Content-Type':'application/json'}});
  }
}
