import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { RoutineService } from '../../providers/routine-service/routine-service';
import { InfoRoutinePage } from '../info-routine/info-routine';
import { FormRoutinePage } from '../form-routine/form-routine';

/**
 * Generated class for the WebUsersPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-routines',
  templateUrl: 'routines.html',
})
export class RoutinesPage {
  formRoutine = FormRoutinePage;
  routines: Array<any>;
  constructor(public navCtrl: NavController, public navParams: NavParams, private service: RoutineService) {
  }

  

  searchForWebUser(event, key) {
    this.service.searchRoutines(event.target.value).subscribe(
      (data: Response) => {
        console.log(data);
        this.routines = data.arrayBuffer.arguments;  
        console.log(data);
      },
      err => {
        console.log(err);
      },
      () => console.log('Routines Search Complete')
    );
  }

  selectRoutine(event, routine){
    console.log(routine);
    this.navCtrl.push(InfoRoutinePage, {
      routine: routine
    })
  }

  allRoutines() {
    this.service.allRoutines().subscribe(
      (data: Array<any>) => {
        this.routines = data;
      },
      err => {
        console.log(err);
      },
      () => console.log('Initial Load Routines')
    );
  }

  ionViewWillEnter(){
    this.allRoutines();
  }

  removeRoutine(event, routine) {
    this.service.removeRoutine(routine['id']);
    this.allRoutines();
  }

  updateRoutine(event, routine) {
    this.navCtrl.push(FormRoutinePage, {
      routine: routine
    })
    this.allRoutines();
  }

}


