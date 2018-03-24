import {Component, Input, OnInit} from '@angular/core';
import {GetAccountService} from "../../../_services/AccountServices/getAccount/get-account.service";
import { PutAccountService } from "../../../_services/AccountServices/putAccount/put-account.service";
import { ActivatedRoute} from "@angular/router";
import { Account } from "../../../beans/Account";
import { Location } from "@angular/common"

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  currentUserId = localStorage.getItem('token.accountId');
  canEdit:boolean = false;
  @Input() account: Account;
  selectedFile: File;

  constructor(
    private route: ActivatedRoute,
    private getAccountService: GetAccountService,
    private putAccountService: PutAccountService,
    private location: Location
  ) { }

  ngOnInit() {
    this.getAccount();
  }

  onFileChanged(event) {
    this.selectedFile = event.target.files[0]
  }

  // onUpload() {
  //   this.http.post('http://localhost:8080/api/account/update', this.selectedFile)
  //     .subscribe(...);
  // }

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
    console.log('goback called');
    this.location.back();
  }

  toggleEdit() {
    this.canEdit = !(this.canEdit);
  }

// Edit form submit
  editSubmit(): void {
    console.log('edit form submitted!');
    this.putAccountService.updateAccount(this.account)
      .subscribe(() => {
        console.log(this.account);
      this.toggleEdit()
      });
  }



}
