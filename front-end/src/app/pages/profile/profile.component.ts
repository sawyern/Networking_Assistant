import { Component, OnInit } from '@angular/core';
import {GetAccountService} from "../../_services/AccountServices/getAccount/get-account.service";
import { UserInfoComponent} from "./user-info/user-info.component";
import { EventListComponent} from "./event-list/event-list.component";
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from "rxjs/Observable";
import { StarComponent} from "./star/star.component";


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
