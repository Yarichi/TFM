import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FormGroup } from '@angular/forms/src/model';
import { FormBuilder, Validators} from '@angular/forms';
import { RoutineService } from '../../providers/routine-service/routine-service';
import { WebuserService } from '../../providers/webuser-service/webuser-service';
import { LocationProvider } from '../../providers/location/location';


/**
 * Generated class for the FormRoutinePage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-form-routine',
  templateUrl: 'form-routine.html',
})
export class FormRoutinePage {


  inputForm: FormGroup;
  taskForm: FormGroup;
  routines: Array<any>;
  routine: {}
  form: Boolean;
  users : any;
  structure : any;
  campus : any;
  buildings : any;
  floors : any;
  zones : any;
  cbf : any;
  prueba: String;
  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              private formBuilder: FormBuilder,
              private service: RoutineService,
              private service_user: WebuserService,
              private service_location : LocationProvider) {
    this.prueba = new Date().toLocaleString();
    this.inputForm = this.formBuilder.group({
      alias_user: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[@_A-Za-z0-9-\\+]+([@_A-Za-z0-9-\\+]*)$'), Validators.required])],
      creator: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$'), Validators.required])],
      date_routine: ['']
    });

    this.taskForm = this.formBuilder.group({
      type: ['', Validators.compose([Validators.maxLength(70), Validators.pattern('^[@_A-Za-z0-9-\\+]+([@_A-Za-z0-9-\\+]*)$'), Validators.required])],
      start_date: [''],
      final_date: [''],
      npc_id : [''],
      room_id : ['']
    });
    this.form = false;
    if(navParams.get('routine') == undefined)
      this.routines = new Array<any>();
    else{
      this.routine = navParams.get('routine');
      console.log(this.routine['tasks']);
      var r = this.routine['tasks'];
      r.forEach(element => {
        var d_start = new Date(element['start_date']*1000).toTimeString();
        var d_end = new Date(element['final_date']*1000).toTimeString();
        var parts_start = d_start.split('\ ')[0].split(':');
        var parts_end = d_end.split('\ ')[0].split(':');
        element['start_date'] = parts_start[0] + ':' + parts_start[1];
        element['final_date'] = parts_end[0] + ':' + parts_end[1];
        console.log(element);
      });
      this.routines = r;
      var date = new Date(this.routine['date_routine']*1000);
      this.inputForm.setValue({'alias_user':this.routine['alias_user'],
                              'creator':this.routine['creator'],
                              'date_routine':date.toISOString()});
      console.log(this.inputForm.value);
      this.campus = []
      this.buildings = []
      this.floors = []
    }
  }

  onAddRoutine(){
    this.service.addRoutine(this.inputForm.value, this.routines);
    this.navCtrl.pop()
  }

  onUpdateRoutine(){
    this.service.modifyRoutine(this.inputForm['id'], this.inputForm);
  }

  onRemoveRoutine(){
    this.service.removeRoutine(this.inputForm['id']);
  }

  showFormTask(){
    this.form = true;
  }
  hideFormTask(){
    this.form = false;
  }

  addTask(){
    this.form = false;
    console.log(this.taskForm.value);
    this.routines.push(this.taskForm.value);
    this.taskForm.reset()
  }

  ionViewWillEnter(){
    this.service_location.getStructureInfo().subscribe(data => {
      console.log(data);
      this.cbf = []
      this.structure = data;
      this.structure['campusCounts'].forEach(element => {  
        var campus = element['campusName']
        if (element['buildingCounts'] != null) {
          element['buildingCounts'].forEach(element2 => {
            var building = element2['buildingName'];
            if (element2['floorCounts'] != null){
              element2['floorCounts'].forEach(element3 => {
                var floor = element3['floorName'];
                this.cbf.push(campus + '>' + building + '>' + floor)
              }); 
            }
          });
        }
      });
    console.log(this.cbf);
    });
    this.service_user.allWebUsersSelect().subscribe(data => this.users = data);
  }

  loadZones(value){
    var parts = value.split('>')
    this.service_location.getInfoMap(parts[0], parts[1], parts[2]).subscribe(data => {
      console.log(data);
      this.zones = data['zones'];
    });
  }

  removeTask(event, index){
    console.log(index);
    this.routines.splice(index, 1);
  }


  ionViewDidLoad() {
    console.log('ionViewDidLoad FormAppuserPage');
  }

  encode(name, lastName, id, alias){
    return btoa(name + "+" + lastName + "+" + id + "+" + alias);
  }

  
  decode(base){
    var parts = atob(base).split('+');
    return parts[0] + " " + parts[1];
  }

}
