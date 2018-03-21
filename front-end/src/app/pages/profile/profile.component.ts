import { Component, OnInit } from '@angular/core';
import {GetAccountService} from "../../_services/getAccount/get-account.service";
import { UserInfoComponent} from "./user-info/user-info.component";
import { EventListComponent} from "./event-list/event-list.component";
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from "rxjs/Observable";


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit {

  email: string;
  phone: string;
  background: string;
  zipcode: string;
  accountId = localStorage.getItem('token.accountId');

  constructor(private getAccountService: GetAccountService) { }

  ngOnInit() {
    this.getAccount();
  }

  //GET account info from server
  getAccount() {
    console.log('getAccount called');
    this.getAccountService.getAccountById(this.accountId)
      .subscribe( data => {
        this.email = data.email;
        this.phone = data.phone;
        this.background = data.background;
        this.zipcode = data.zipCode;
      })
  }

}
