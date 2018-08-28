import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as Constants from '../../inner-resources/constants';

/*
  Generated class for the LocationProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class LocationProvider {

  map : any;
  constructor(public http: HttpClient) {
    console.log('Hello Location Provider');
  }

  getStructureInfo(){
    return this.http.get(Constants.BASE_URL_LOCATION_API + '/location/structure');
  }

  getInfoMap(campus, building, floor){
    return this.http.get(Constants.BASE_URL_LOCATION_API + '/location/map/info/campus/'+campus+'/building/'+building+'/floor/'+floor)
  }
}
