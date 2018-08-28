import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AppUsersPage } from './app-users';

@NgModule({
  declarations: [
    AppUsersPage,
  ],
  imports: [
    IonicPageModule.forChild(AppUsersPage),
  ],
})
export class AppUsersPageModule {}
