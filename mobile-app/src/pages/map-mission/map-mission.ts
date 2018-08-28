import { Component, ViewChild, ElementRef } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController } from 'ionic-angular';
import { LocationProvider } from '../../providers/location/location';
import { Events } from 'ionic-angular';

import * as Constants from '../../inner-resources/constants';


/**
 * Generated class for the MapMissionPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-map-mission',
  templateUrl: 'map-mission.html',
})
export class MapMissionPage {

  @ViewChild('divMap')
  divMap:ElementRef;

  @ViewChild('iconPosition')
  iconPosition:ElementRef;

  mission : MissionModel;
  map : String;
  info_user : InfoUserModel;
  campus : String;
  building : String;
  floor : String;
  zone : String;
  info_map : InfoMapModel;
  interval : any;
  distance : number;
  unit : string;

  x_position_user : number;
  y_position_user : number;

  x_position_image : number;
  y_position_image : number;

  x_position_room : number;
  y_position_room : number;

  x_position_user_actual : number;
  y_position_user_actual : number;

  meters_away : number;
  angle : number;

  loading_show : any;
  constructor(public navCtrl: NavController, public navParams: NavParams, private service : LocationProvider, private events : Events, private loading : LoadingController) {
    this.mission = this.navParams.get('mission');
    events.subscribe('map:loaded', () => {
      // user and time are the same arguments passed in `events.publish(user, time)`
      this.service.getInfoMap(this.campus, this.building, this.floor).subscribe(data3 => {
        this.loadInfo(data3);
      });
      
    });

    events.subscribe('load:finished', () => {
      this.service.getCoordinateZone(this.campus, this.building, this.floor, this.mission.room_id).subscribe(data4 => {
        this.x_position_room = data4['x'];
        this.y_position_room = data4['y'];
        this.loadGuide();
      });
    });

    events.subscribe('load:finished', () => {
      // user and time are the same arguments passed in `events.publish(user, time)`
      this.loading_show.dismiss();
      this.updatePosition();
    });
    
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad MapMissionPage');
  }

  loadData(data){
    this.map = data['detail'];
  }
  
  loadUser(data){
    this.info_user = data;
    var hierarchy : String = data['locationMapHierarchy'];
    var parts : String[] = hierarchy.split(">");
    this.campus = parts[0];
    this.building = parts[1];
    this.floor = parts[2];
    this.unit = this.info_user.locationCoordinate.unit;
  }


  loadPosition(){
    this.x_position_user = (this.info_map.image.width * this.info_user.locationCoordinate.x) / this.info_map.dimension.width;
    this.y_position_user = (this.info_map.image.height * this.info_user.locationCoordinate.y) / this.info_map.dimension.height;

    // if (this.x_position_user_actual == null){
    //   this.x_position_user_actual = this.x_position_user;
    //   this.y_position_user_actual = this.y_position_user;

    //   this.x_position_user_previous = null;
    //   this.y_position_user_previous = null;
    // }

    // else{
    //   this.x_position_user_previous = this.x_position_user_actual;
    //   this.y_position_user_previous = this.y_position_user_actual;
      
    //   this.x_position_user_actual = this.x_position_user;
    //   this.y_position_user_actual = this.y_position_user;

    // }
    /**
     * Image position
     */
    this.x_position_image = this.x_position_user - (this.divMap.nativeElement.offsetWidth / 2);
    this.y_position_image = this.y_position_user - (this.divMap.nativeElement.offsetHeight);

    this.divMap.nativeElement.style.backgroundPosition = this.x_position_image + "px, " + this.y_position_image + "px";
  }

  loadInfo(data){
    this.info_map = data;
    this.loadPosition();

    /**
     * Icon position
     */
    this.iconPosition.nativeElement.style.position = "fixed";
    this.iconPosition.nativeElement.style.left = this.divMap.nativeElement.offsetWidth / 2 + "px";
    this.iconPosition.nativeElement.style.bottom = "32px";

    
    this.events.publish('load:finished');
    
  }


  loadGuide(){
    this.distance = parseInt(Math.sqrt(Math.pow((this.x_position_user - this.x_position_room), 2) + Math.pow((this.y_position_user - this.y_position_room), 2)).toString());

    var P_1X = this.x_position_user;
    var P_1Y = this.y_position_user;

    var P_2X = this.x_position_user;
    if (this.y_position_user == 0) var P_2Y = this.y_position_user + 1;
    else var P_2Y = this.y_position_user - 1;

    var P_3X = this.x_position_room;
    var P_3Y = this.y_position_room;


    var P_12 = Math.sqrt(Math.pow((P_1X - P_2X), 2) + Math.pow(P_1Y - P_2Y, 2));
    var P_13 = Math.sqrt(Math.pow((P_1X - P_3X), 2) + Math.pow(P_1Y - P_3Y, 2));
    var P_23 = Math.sqrt(Math.pow((P_2X - P_3X), 2) + Math.pow(P_2Y - P_3Y, 2));
    this.angle = (Math.acos((Math.pow(P_12, 2) + Math.pow(P_13, 2) - Math.pow(P_23, 2)) / (2 * P_12 * P_13))) * (180 / Math.PI);

    // ROOM IN LEFT
    if (this.x_position_room < this.x_position_user) this.angle = 360 - this.angle;
    console.log(this.distance);
  }

  updatePosition(){
    var self = this;
    this.interval = setInterval(function(){
      console.log("Update...");
      self.service.getInfoByIp().subscribe(
        (data: any) => {
          self.loadUser(data);
          self.loadPosition();
          self.loadGuide();
        },
        err => {
          console.log(err);
        },
        () => console.log('Update Map')
      );
      
    }, Constants.TIME_UPDATE_LOCATION);
  }

  ionViewWillEnter(){
    this.loading_show = this.loading.create({
      content: 'Please wait...'
    });
  
    this.loading_show.present();

    this.service.getInfoByIp().subscribe(
      (data: any) => {
        this.loadUser(data);
        new Promise(resolve => this.service.getMapCBF(this.campus, this.building, this.floor).subscribe(data2 => {
          this.map = data2;
          console.log(data2);
          this.events.publish('map:loaded');
        }));
      },
      err => {
        console.log(err);
      },
      () => console.log('Initial Load Map')
    );
  }
  

  ionViewWillLeave(){
    clearInterval(this.interval);
    this.events.unsubscribe('map:loaded');
    this.events.unsubscribe('load:finished');
  }

  ionViewDidLeave(){
    clearInterval(this.interval);
    this.events.unsubscribe('map:loaded');
    this.events.unsubscribe('load:finished');
  }

}
