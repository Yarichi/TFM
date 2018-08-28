import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfigServiceProvider } from '../../providers/config-service/config-service';

/**
 * Generated class for the IndoorSettingsPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-indoor-settings',
  templateUrl: 'indoor-settings.html',
})
export class IndoorSettingsPage {

  settingsForm: FormGroup;
  settings : IndoorSettingsModel;
  constructor(public navCtrl: NavController, public navParams: NavParams, private formBuilder: FormBuilder, private service : ConfigServiceProvider) {
    this.settingsForm = this.formBuilder.group({
      manufacturer: ['', ''],
      url: ['', ''],
      username: ['', ''],
      password: ['','']
    });
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad IndoorSettingsPage');
  }

  ionViewWillEnter(){
    this.service.getSettings().subscribe((data : IndoorSettingsModel) => {
      this.settings = data;
      this.settings.manufacturer = "Cisco";
      this.settingsForm.setValue(this.settings);
    });
  }

  onUpdateSettings(){
    this.service.setSettings(this.settingsForm.value);
  }

}
