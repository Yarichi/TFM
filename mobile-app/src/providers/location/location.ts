import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { NetworkInterface } from '@ionic-native/network-interface';
import * as Constants from '../../inner-resources/constants';

/*
  Generated class for the LocationProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class LocationProvider {

  map : any;
  constructor(public http: HttpClient, private networkInterface : NetworkInterface) {
    console.log('Hello Location Provider');
  }

  getMapCBF(campus, building, floor){
    return this.http.get(Constants.BASE_URL_LOCATION_API + '/location/map/campus/'+campus+'/building/'+building+'/floor/'+floor, {responseType: 'text'});
  }

  getInfoByIp(){
    return this.http.get(Constants.BASE_URL_LOCATION_API + '/location/user/'+btoa('10.10.20.216'))
  }

  getInfoMap(campus, building, floor){
    return this.http.get(Constants.BASE_URL_LOCATION_API + '/location/map/info/campus/'+campus+'/building/'+building+'/floor/'+floor)
  }

  getCoordinateZone(campus, building, floor, zone){
    return this.http.get(Constants.BASE_URL_LOCATION_API + '/location/map/info/campus/'+campus+'/building/'+building+'/floor/'+floor+'/zone/'+btoa(zone))
  }

}
