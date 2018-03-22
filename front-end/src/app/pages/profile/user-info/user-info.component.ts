import {Component, Input, OnInit} from '@angular/core';
import {GetAccountService} from "../../../_services/getAccount/get-account.service";
import { PutAccountService } from "../../../_services/putAccount/put-account.service";
import { ActivatedRoute} from "@angular/router";
import { Account } from "../../../beans/Account";
import { Location } from "@angular/common"

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  currentUserId = localStorage.getItem('token.accountId')
  canEdit:boolean = false;
  @Input() account: any;

  constructor(
    private route: ActivatedRoute,
    private getAccountService: GetAccountService,
    private putAccountService: PutAccountService,
    private location: Location
  ) { }

  ngOnInit() {
    this.getAccount();
  }

  //GET account info from server
  getAccount(): void {
    console.log('getAccount called');
    const accountId = this.route.snapshot.paramMap.get('accountId');
    this.getAccountService.getAccountById(accountId)
      .subscribe( account => {
        this.account = account;
      })
  }

  goBack(): void {
    this.location.back();
  }

  toggleEdit() {
    this.canEdit = !(this.canEdit);
  }

  save(): void {
    this.putAccountService.updateAccount(this.account)
      .subscribe(() => this.goBack());
  }



}
