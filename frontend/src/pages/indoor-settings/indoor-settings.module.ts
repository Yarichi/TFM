import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { IndoorSettingsPage } from './indoor-settings';

@NgModule({
  declarations: [
    IndoorSettingsPage,
  ],
  imports: [
    IonicPageModule.forChild(IndoorSettingsPage),
  ],
})
export class IndoorSettingsPageModule {}
