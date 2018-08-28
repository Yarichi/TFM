import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { FormContactPage } from './form-contact';

@NgModule({
  declarations: [
    FormContactPage,
  ],
  imports: [
    IonicPageModule.forChild(FormContactPage),
  ],
})
export class FormContactPageModule {}
