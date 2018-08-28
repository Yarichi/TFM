import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { MissionsProvider } from '../../providers/missions/missions';
import { InfoMissionPage } from '../info-mission/info-mission';


/**
 * Generated class for the MissionsPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-missions',
  templateUrl: 'missions.html',
})
export class MissionsPage {
  missions: Array<MissionModel>;
  
  constructor(public navCtrl: NavController, public navParams: NavParams, private service : MissionsProvider) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad MissionsPage');
  }

  loadData(data){
    this.missions = data;
    this.missions.forEach(element => {
      var start_date = new Date(element.start_date * 1000);
      var final_date = new Date(element.final_date * 1000);
      console.log("TIMESTAMP")
      console.log(element.start_date)
      console.log(element.final_date)
      element.start_date_show = start_date.getHours() + ":" + ((start_date.getMinutes() <= 9) ? ("0" + start_date.getMinutes()) : start_date.getMinutes());
      element.final_date_show = final_date.getHours() + ":" + ((final_date.getMinutes() <= 9) ? ("0" + final_date.getMinutes()) : final_date.getMinutes());;
      console.log("A MOSTRAR")
      console.log(element.start_date_show)
      console.log(element.final_date_show)
    });
  }

  ionViewWillEnter(){
    new Promise(resolve => {this.service.getMissions().then(data => {this.loadData(data)})});
  }


  onInfoMission(event, mission){
    this.navCtrl.push(InfoMissionPage, {'mission': mission});
  }

  checkTimestamp(mission){
    var now = new Date().getTime();
    var start = (now >= mission.start_date * 1000);
    var end = (now <= mission.final_date * 1000)
    if (start && end) return 1;
    else if (!start) return 2;
    else if (!end) return 3;
  }
}
