import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { WebUsersPage } from './web-users';

@NgModule({
  declarations: [
    WebUsersPage,
  ],
  imports: [
    IonicPageModule.forChild(WebUsersPage),
  ],
})
export class WebUsersPageModule {}
