import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { MapMissionPage } from './map-mission';

@NgModule({
  declarations: [
    MapMissionPage,
  ],
  imports: [
    IonicPageModule.forChild(MapMissionPage),
  ],
})
export class MapMissionPageModule {}
