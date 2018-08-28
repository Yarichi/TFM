import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AppUsersProvider } from '../../providers/app-users/app-users';
import { AddFriendPage } from '../add-friend/add-friend';

/**
 * Generated class for the FriendsPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-friends',
  templateUrl: 'friends.html',
})
export class FriendsPage {
  alias: String;
  friends: any;
  friends_alias: any;
  token: String;
  constructor(public navCtrl: NavController, public navParams: NavParams, public service: AppUsersProvider) {
    this.alias = localStorage.getItem('alias');
    this.token = localStorage.getItem('token');
    this.loadFriends();
  }

  loadFriends(){
    this.service.searchAppUserFriends(this.alias, this.token).subscribe(
      (data: any) => {
        console.log(data);
        this.friends = data.friends;
        this.friends_alias = new Array<String>();
        this.friends.forEach(element => {
          this.friends_alias.push(element.alias);
        });
      },
      err => {
        console.log(err);
      },
      () => console.log('Initial Load Friends')
    );
  }

  ionViewWillEnter(){
    console.log("RELOAD");
    this.loadFriends();
  }

  onAddFriend(){
    this.navCtrl.push(AddFriendPage, {'friends': this.friends_alias});
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad FriendsPage');
  }

  removeFriend(event, friendAlias){
    new Promise(resolve => { this.service.removeFriend(this.alias, friendAlias).then(data => {this.loadFriends()})});
  }


}
