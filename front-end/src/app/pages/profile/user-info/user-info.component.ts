import { Component, OnInit } from '@angular/core';
import {GetAccountService} from "../../../_services/getAccount/get-account.service";
import { ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  email: string;
  phone: string;
  background: string;
  zipcode: string;
  accountId: string;

  constructor(
    private route: ActivatedRoute,
    private getAccountService: GetAccountService) { }

  ngOnInit() {
    this.getAccount();
  }

  //GET account info from server
  getAccount() {
    console.log('getAccount called');
    const accountId = this.route.snapshot.paramMap.get('accountId');
    console.log(accountId);
    this.getAccountService.getAccountById(accountId)
      .subscribe( data => {
        this.accountId = data.id;
        this.email = data.email;
        this.phone = data.phone;
        this.background = data.background;
        this.zipcode = data.zipCode;
      })
  }

}
