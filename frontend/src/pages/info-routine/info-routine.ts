import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

/**
 * Generated class for the InfoRoutinePage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-info-routine',
  templateUrl: 'info-routine.html',
})
export class InfoRoutinePage {

  routine: {};

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.routine = navParams.get('routine');
  }

}
