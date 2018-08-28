import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { FormRoutinePage } from './form-routine';

@NgModule({
  declarations: [
    FormRoutinePage,
  ],
  imports: [
    IonicPageModule.forChild(FormRoutinePage),
  ],
})
export class FormRoutinePageModule {}
