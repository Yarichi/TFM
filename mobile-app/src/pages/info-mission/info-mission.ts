import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { MapMissionPage } from '../map-mission/map-mission';

/**
 * Generated class for the InfoMissionPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-info-mission',
  templateUrl: 'info-mission.html',
})
export class InfoMissionPage {

  mission : MissionModel;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.mission = this.navParams.get('mission');
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad InfoMissionPage');
  }

  checkEnable(){
    var now = new Date().getTime();
    return !((now >= this.mission.start_date * 1000) &&
    (now <= this.mission.final_date * 1000));
  }

  onStartMission(){
    this.navCtrl.push(MapMissionPage, {'mission': this.mission});
  }

}
