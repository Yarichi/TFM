import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AppUsersProvider } from '../../providers/app-users/app-users';

/**
 * Generated class for the AddFriendPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-add-friend',
  templateUrl: 'add-friend.html',
})
export class AddFriendPage {
  alias: String;
  users: any;
  token: String;
  friends: any;
  constructor(public navCtrl: NavController, public navParams: NavParams, public service: AppUsersProvider) {
    this.alias = localStorage.getItem('alias');
    this.token = localStorage.getItem('token');
    this.friends = navParams.get('friends');
  }

  ionViewWillEnter(){
    this.loadUsers();
  }
  loadUsers(){
    this.service.searchAppUsers().subscribe(
      (data: any) => {
        console.log(this.friends);
        this.users = new Array<any>();
        this.users = data.filter((item) => {
            if (item.alias == this.alias) {
              return false;
            }
            if(this.friends.indexOf(item.alias) != -1){
              return false;
            }
            return true;
          });
      },
      err => {
        console.log(err);
      },
      () => console.log('Initial Load Users')
    );
  }

  loadFriendsAndUsers(){
    this.service.searchAppUserFriends(this.alias, this.token).subscribe(
      (data: any) => {
        var aux = data.friends;
        this.friends = new Array<String>();
        aux.forEach(element => {
          this.friends.push(element.alias);
        });
        this.loadUsers();
      },
      err => {
        console.log(err);
      },
      () => console.log('Initial Load Friends')
    );
  }

  addFriend(event, aliasFriend){
    new Promise(resolve => {this.service.addFriendAppUser(this.alias, aliasFriend).then(data => {this.loadFriendsAndUsers()})});
  }
  ionViewDidLoad() {
    console.log('ionViewDidLoad AddFriendsPage');
  }



}
