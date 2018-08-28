import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { TaskMapsPage } from './task-maps';

@NgModule({
  declarations: [
    TaskMapsPage,
  ],
  imports: [
    IonicPageModule.forChild(TaskMapsPage),
  ],
})
export class TaskMapsPageModule {}
